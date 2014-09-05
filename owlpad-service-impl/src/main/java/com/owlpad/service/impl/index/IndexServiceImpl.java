package com.owlpad.service.impl.index;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.owlpad.domain.index.IndexRequest;
import com.owlpad.domain.index.IndexResponse;
import com.owlpad.service.index.IndexService;

/**
 * Apache Lucene indexService implementation
 * 
 * @author Jay Paulynice
 *
 */
@Service("index")
public class IndexServiceImpl implements IndexService{
	private static final Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see com.owlpad.service.index.IndexService#index(com.owlpad.domain.index.IndexRequest)
	 */
	@Override
	public Response index(final IndexRequest indexRequest){
		IndexResponse response = new IndexResponse();
		
		Directory indexDir = null;
		int numIndexed = 0;
		try {
			indexDir = FSDirectory.open(new File("/temp/owpad-index"));
			String dataDirPath = indexRequest.getDirectoryToIndex();
			File dataDir = new File(dataDirPath);
			
			numIndexed = indexDir(indexDir, dataDir, indexRequest.getSuffix());
			
			response.setDocumentsIndexed(numIndexed);
		} catch (IOException e) {
			logger.info("Exception while calling index.  Exception: "+ e);
			return Response.serverError().build();
		}
		

		GenericEntity<IndexResponse> entity = new GenericEntity<IndexResponse>(response){};
		return Response.ok(entity).build();		
	}

	/**
     * Method to index a directory by passing the directory to index, location to store the index, and word endings.
     * 
     * @param indexDir
     * @param dataDir
     * @param suffix
     * @return
	 * @throws IOException 
     * @throws Exception
     */
    private int indexDir(final Directory indexDir,final File dataDir,final String suffix) throws IOException {
      
    	StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_48);
    	IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
    	IndexWriter indexWriter = new IndexWriter(indexDir, config);      
        indexDirectory(indexWriter, dataDir, suffix);
        
        int numIndexed = indexWriter.maxDoc();
		indexWriter.close();
	
        return numIndexed;
        
    }
    
    /**
     * Method to index a directory recursively.
     * 
     * @param indexWriter
     * @param dataDir
     * @param suffix
     * @throws IOException
     */
    private void indexDirectory(final IndexWriter indexWriter,final  File dataDir,final String suffix) throws IOException {

        File[] files = dataDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (f.isDirectory()) {
                indexDirectory(indexWriter, f, suffix);
            }
            else {
            	indexFileWithIndexWriter(indexWriter, f, suffix);
			}
        }

    }
    
    /**
     * Index a file by creating a Document and adding fields
     * 
     * @param indexWriter
     * @param f
     * @param suffix
     * @throws IOException
     */
    private void indexFileWithIndexWriter(final IndexWriter indexWriter,final File f,final String suffix) throws IOException {

        if (f.isHidden() || f.isDirectory() || !f.canRead() || !f.exists()) {
            return;
        }
        if (suffix!=null && !f.getName().endsWith(suffix)) {
            return;
        }
        
        Document doc = new Document();
        doc.add(new Field("contents", new FileReader(f),TextField.TYPE_NOT_STORED));        
        doc.add(new StringField("filepath", f.getCanonicalPath(), Field.Store.YES));
        doc.add(new StringField("filename", f.getName(), Field.Store.YES));
        
        indexWriter.addDocument(doc);
    }
}