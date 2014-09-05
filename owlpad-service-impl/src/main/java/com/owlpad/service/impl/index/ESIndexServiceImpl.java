package com.owlpad.service.impl.index;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import com.google.common.base.Preconditions;
import com.owlpad.domain.index.IndexRequest;
import com.owlpad.domain.index.IndexResponse;
import com.owlpad.elasticsearch.client.NodeClientFactoryBean;
import com.owlpad.service.index.IndexService;

/**
 * Elasticsearch indexService implementation.
 * 
 * @author Jay Paulynice
 *
 */
@Service("indexService")
public class ESIndexServiceImpl implements IndexService {
	private final NodeClientFactoryBean nodeClientFactoryBean;
	private final NodeClient client;
	private static final Logger logger = LoggerFactory.getLogger(ESIndexServiceImpl.class);
	
	@Autowired
	public ESIndexServiceImpl(NodeClientFactoryBean nodeClientFactoryBean) throws Exception{
		this.nodeClientFactoryBean = nodeClientFactoryBean;
		this.client = this.nodeClientFactoryBean.getObject();
	}

	/*
	 * (non-Javadoc)
	 * @see com.owlpad.service.index.IndexService#index(com.owlpad.domain.index.IndexRequest)
	 */
	@Override
	public Response index(final IndexRequest indexRequest) {
		Preconditions.checkNotNull(indexRequest.getDirectoryToIndex(),"No directory specified for indexing...");
		
		IndexResponse response = new IndexResponse();
		String suffix = indexRequest.getSuffix();
		String dataDirPath = indexRequest.getDirectoryToIndex();
		File dataDir = new File(dataDirPath);

		try {
			response.setDocumentsIndexed(indexDir(dataDir, suffix));
		}
		catch (Exception e) {
			logger.info("Exception while calling index.  Exception" + e);
			return Response.serverError().build();
		}

		GenericEntity<IndexResponse> entity = new GenericEntity<IndexResponse>(response){};
		return Response.ok(entity).build();	
	}

	/**
	 * Entry point for directory indexing 
	 * 
	 * @param dataDir
	 * @param suffix
	 * @return
	 * @throws Exception 
	 */
	private int indexDir(File dataDir, String suffix) throws Exception {
		try {
			CreateIndexRequestBuilder cirb = client.admin().indices().prepareCreate("owlpad-index");
			cirb.execute().actionGet();
		} catch (IndexAlreadyExistsException e) {
			logger.info("Could not create index because it exists already.",e);
		}

		BulkRequestBuilder br = client.prepareBulk();

		List<File> filesToIndex = new ArrayList<File>();
		getFilesFromDirectory(dataDir, filesToIndex,suffix);
		addDocumentsToBulkRequest(br, filesToIndex);
		if (filesToIndex.size() > 0) {
			BulkResponse bResponse = br.execute().actionGet();
			return bResponse.getItems().length;
		}

		return 0;
	}

	/**
	 * Get files from a directory recursively.  If the suffix is null then 
	 * index all files in the directory.  Otherwise index only files with 
	 * the suffix ending.
	 * 
	 * @param dataDir
	 * @param filesToIndex
	 * @param suffix
	 * @throws IOException
	 */
	private void getFilesFromDirectory(File dataDir, List<File> filesToIndex,String suffix) throws IOException {
		File[] files = dataDir.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				getFilesFromDirectory(f, filesToIndex,suffix);
			} else {
				if(suffix == null || f.getCanonicalPath().endsWith(suffix)){
					filesToIndex.add(f);
				}
			}
		}
	}

	/**
	 * Build {@link BulkRequestBuilder} object 
	 * 
	 * @param bulkRequest
	 * @param filesToIndex
	 * @throws Exception
	 */
	private void addDocumentsToBulkRequest(BulkRequestBuilder bulkRequest, List<File> filesToIndex) throws Exception {
		for (File f : filesToIndex) {
			IndexRequestBuilder rb = createIndexRequestBuilderFromFile(f);
			if(rb != null){
				bulkRequest.add(rb);				
			}
		}
	}

	/**
	 * Create an indexRequestBuilder object give the client, file, id,and content
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private IndexRequestBuilder createIndexRequestBuilderFromFile(File f) throws Exception{
		String filePath = f.getCanonicalPath();
		int indexOfDot = filePath.lastIndexOf(".");
		String docType = filePath.substring(indexOfDot);
		
		List<String> excludes = Arrays.asList(".class",".jar",".war", ".classpath",".project", ".ear",".settings",".prefs");
		boolean unreadable = f.isHidden() || f.isDirectory() || !f.canRead() || !f.exists();
		if (!unreadable && !excludes.contains(docType)) {
			String content = FileUtils.readFileToString(f);
			Path path = Paths.get(filePath);
			String author = Files.getOwner(path).getName();
			BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
			
			XContentBuilder source = getSource(content, filePath, f.getName(), author, attr, docType);
			
			return client.prepareIndex("owlpad-index", "docs").setSource(source);
		}
		return null;
	}
	
	/**
	 * Get json source from file attributes to index
	 * 
	 * @param content
	 * @param filePath
	 * @param fileName
	 * @param author
	 * @param attr
	 * @param docType
	 * @return
	 * @throws IOException
	 */
	private XContentBuilder getSource(String content, String filePath, String fileName, String author,BasicFileAttributes attr, String docType) throws IOException{
		XContentBuilder builder = jsonBuilder().startObject()
			.field("contents", content)
			.field("filepath",filePath)
			.field("filename", fileName)
			.field("author", author)
			.field("created", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format((attr.creationTime().toMillis())))
			.field("lastModified", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format((attr.lastModifiedTime().toMillis())))
			.field("size", String.valueOf(attr.size()))
			.field("docType",docType)
			.endObject();
		
		return builder;
	}
}
