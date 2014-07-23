package com.owlpad.service.impl.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	private static final Logger logger = LoggerFactory.getLogger(ESIndexServiceImpl.class);

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
		} catch (IOException e) {
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
	 * @throws IOException
	 */
	private int indexDir(File dataDir, String suffix) throws IOException {
		Client client = ESClientProvider.INSTANCE;
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
		addDocumentsToBulkRequest(client, bulkRequest, filesToIndex, suffix);
		if (filesToIndex.size() > 0) {
			bulkRequest.execute().actionGet();
		}

		client.close();
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
	 * @throws IOException
	 */
	private void addDocumentsToBulkRequest(Client client,
			BulkRequestBuilder bulkRequest, List<File> filesToIndex,
			String suffix) throws IOException {
		int num = 1;
		for (File f : filesToIndex) {
			boolean unReadable = f.isHidden() || f.isDirectory() || !f.canRead() || !f.exists();
			boolean matchSuffix = suffix != null && f.getName().endsWith(suffix);
			if (!unReadable && matchSuffix) {
				BufferedReader br;

				try {
					br = new BufferedReader(new FileReader(f));
					StringBuilder sb = new StringBuilder();
					String line = br.readLine();

					while (line != null) {
						sb.append(line);
						sb.append(" ");
						line = br.readLine();
					}
					br.close();

					IndexRequestBuilder requestBuilder = getIndexRequestBuilder(client,f,num,sb.toString());
					bulkRequest.add(requestBuilder);
					num++;

				} catch (IOException e) {
					logger.info("Exception while calling indexFileWithIndexWriter: "+ e);
				}
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
	private IndexRequestBuilder getIndexRequestBuilder(Client client,File f, int id, String content) throws IOException{
		return client.prepareIndex("owlpad-index", "docs",String.valueOf(id))
				.setSource(jsonBuilder().startObject()
						.field("contents", content)
						.field("filepath",f.getCanonicalPath())
						.field("filename", f.getName())
						.endObject());
	}
}
