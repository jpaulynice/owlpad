package com.owlpad.service.impl.index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.helpers.FileUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import com.owlpad.domain.index.IndexRequest;
import com.owlpad.domain.index.IndexResponse;
import com.owlpad.domain.search.StatusType;
import com.owlpad.service.esclient.ESClientProvider;
import com.owlpad.service.index.IndexService;

/**
 * Elasticsearch indexService implementation.
 * 
 * @author Jay Paulynice
 *
 */
@Service("esIndexService")
public class ESIndexServiceImpl implements IndexService {
	Client client;
	private static final Logger logger = LoggerFactory.getLogger(ESIndexServiceImpl.class);
	
	public static void main(String[] args){
		IndexService s = new ESIndexServiceImpl();
		IndexRequest ir = new IndexRequest();
		ir.setDirectoryPath("/Users/julespaulynice/Desktop");
		ir.setSuffix(".java");
		
		System.out.println(s.index(ir));
	}
	
	public ESIndexServiceImpl(){
		client = ESClientProvider.getInstance();
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

		List<File> filesToIndex = new ArrayList<>();
		getFilesFromDirectory(dataDir, filesToIndex);
		addDocumentsToBulkRequest(bulkRequest, filesToIndex, suffix);
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
	 */
	private void getFilesFromDirectory(File dataDir, List<File> filesToIndex) {
		File[] files = dataDir.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				getFilesFromDirectory(f, filesToIndex);
			} else {
				filesToIndex.add(f);
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
	private void addDocumentsToBulkRequest(BulkRequestBuilder bulkRequest, List<File> filesToIndex,
			String suffix) throws Exception {
		int num = 1;
		for (File f : filesToIndex) {
			boolean unReadable = f.isHidden() || f.isDirectory() || !f.canRead() || !f.exists();
			boolean matchSuffix = suffix != null && f.getName().endsWith(suffix);
			if (!unReadable && matchSuffix) {
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
		return client.prepareIndex("owlpad-index", "docs",String.valueOf(id))
				.setSource(jsonBuilder().startObject()
						.field("contents", content)
						.field("filepath",f.getCanonicalPath())
						.field("filename", f.getName())
						.endObject());
	}
}
