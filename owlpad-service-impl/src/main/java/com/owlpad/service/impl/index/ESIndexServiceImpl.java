package com.owlpad.service.impl.index;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.helpers.FileUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import com.owlpad.domain.index.IndexRequest;
import com.owlpad.domain.index.IndexResponse;
import com.owlpad.domain.search.StatusType;
import com.owlpad.service.elasticsearch.client.NodeClientFactoryBean;
import com.owlpad.service.index.IndexService;

/**
 * Elasticsearch indexService implementation.
 * 
 * @author Jay Paulynice
 *
 */
@Service("indexService")
public class ESIndexServiceImpl implements IndexService {
	NodeClientFactoryBean nodeClientFactoryBean;
	NodeClient client;
	private static final Logger logger = LoggerFactory.getLogger(ESIndexServiceImpl.class);
	
	@Autowired
	public ESIndexServiceImpl(NodeClientFactoryBean nodeClientFactoryBean) throws Exception{
		this.nodeClientFactoryBean = nodeClientFactoryBean;
		client = this.nodeClientFactoryBean.getObject();
	}

	/*
	 * (non-Javadoc)
	 * @see com.owlpad.service.index.IndexService#index(com.owlpad.domain.index.IndexRequest)
	 */
	@Override
	public IndexResponse index(IndexRequest indexRequest) {
		IndexResponse response = new IndexResponse();
		String suffix = indexRequest.getSuffix();
		String dataDirPath = indexRequest.getDirectoryPath();
		File dataDir = new File(dataDirPath);

		try {
			response.setDocumentsIndexed(indexDir(dataDir, suffix));
			response.setStatus(StatusType.SUCCESS);
		}
		catch (Exception e) {
			response.setStatus(StatusType.FAIL);
			response.setErrorMessage(e.toString());
			logger.info("Exception while calling index.  Exception" + e);
		}

		return response;
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
			CreateIndexRequestBuilder createIndexRequestBuilder = client
					.admin().indices().prepareCreate("owlpad-index");
			createIndexRequestBuilder.execute().actionGet();
		} catch (IndexAlreadyExistsException e) {
			logger.info("Could not create index because it exists already.  Exception: "+e);
		}

		BulkRequestBuilder bulkRequest = client.prepareBulk();

		List<File> filesToIndex = new ArrayList<File>();
		getFilesFromDirectory(dataDir, filesToIndex,suffix);
		addDocumentsToBulkRequest(bulkRequest, filesToIndex);
		if (filesToIndex.size() > 0) {
			bulkRequest.execute().actionGet();
		}

		return filesToIndex.size();
	}

	/**
	 * Get files from a directory
	 * 
	 * @param dataDir
	 * @param filesToIndex
	 * @throws IOException 
	 */
	private void getFilesFromDirectory(File dataDir, List<File> filesToIndex,String suffix) throws IOException {
		File[] files = dataDir.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				getFilesFromDirectory(f, filesToIndex,suffix);
			} else {
				if(f.getCanonicalPath().endsWith(suffix) || suffix == null){
					filesToIndex.add(f);
				}
			}
		}
	}

	/**
	 * Build up {@link BulkRequestBuilder} object 
	 * 
	 * @param client
	 * @param bulkRequest
	 * @param filesToIndex
	 * @param suffix
	 * @throws Exception 
	 */
	private void addDocumentsToBulkRequest(BulkRequestBuilder bulkRequest, List<File> filesToIndex) throws Exception {
		int num = 1;
		for (File f : filesToIndex) {
			boolean unReadable = f.isHidden() || f.isDirectory() || !f.canRead() || !f.exists();
			if (!unReadable) {
				StringBuilder sb = new StringBuilder();
				List<String> lines = FileUtils.readLines(f);
				for(String line: lines){
					sb.append(line);
					sb.append(" ");
				}

				IndexRequestBuilder requestBuilder = getIndexRequestBuilder(f,num,sb.toString());
				bulkRequest.add(requestBuilder);
				num++;
			}
		}
	}

	/**
	 * Create an indexRequestBuilder object give the client, file, id,and content
	 * 
	 * @param client
	 * @param f
	 * @param id
	 * @param content
	 * @return {@link IndexRequestBuilder} object
	 * @throws IOException
	 */
	private IndexRequestBuilder getIndexRequestBuilder(File f, int id, String content) throws IOException{
		
		Path path = Paths.get(f.getCanonicalPath());
		UserPrincipal owner = Files.getOwner(path);
		String author = owner.getName();
		BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
		
		
		return client.prepareIndex("owlpad-index", "docs",String.valueOf(id))
				.setSource(jsonBuilder().startObject()
						.field("contents", content)
						.field("filepath",f.getCanonicalPath())
						.field("filename", f.getName())
						.field("author", author)
						.field("created", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format((attr.creationTime().toMillis())))
						.field("lastModified", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format((attr.lastModifiedTime().toMillis())))
						.field("size", String.valueOf(attr.size()))
						.endObject());
	}
}
