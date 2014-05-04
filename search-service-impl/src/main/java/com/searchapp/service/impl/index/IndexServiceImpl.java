package com.searchapp.service.impl.index;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
import org.springframework.stereotype.Service;

import com.searchapp.domain.index.IndexRequest;
import com.searchapp.domain.index.IndexResponse;
import com.searchapp.service.index.IndexService;

/**
 * 
 * @author Jay Paulynice
 *
 */
@Service("indexService")
public class IndexServiceImpl implements IndexService{
	
	public IndexServiceImpl(){
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.searchapp.index.service.IndexService#index(com.searchapp.index.domain.IndexRequest)
	 */
	@Override
	public IndexResponse index(IndexRequest indexRequest) throws Exception{
		IndexResponse response = new IndexResponse();
		
		String indexDirPath= indexRequest.getIndexDirectoryPath();
		Directory indexDir = FSDirectory.open(new File(indexDirPath));
		
		String dataDirPath = indexRequest.getDirectoryPath();
		File dataDir = new File(dataDirPath);
		
		int numIndexed = indexDir(indexDir, dataDir, indexRequest.getSuffix());
		response.setDocumentsIndexed(numIndexed);
		
		return response;
	}

	/**
     * Method to index a directory by passing the directory to index, location to store the index, and word endings.
     * 
     * @param indexDir
     * @param dataDir
     * @param suffix
     * @return
     * @throws Exception
     */
    private int indexDir(Directory indexDir, File dataDir, String suffix) throws Exception {
      
    	StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
    	IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
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
    private void indexDirectory(IndexWriter indexWriter, File dataDir, 
           String suffix) throws IOException {

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
    private void indexFileWithIndexWriter(IndexWriter indexWriter, File f, 
            String suffix) throws IOException {

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