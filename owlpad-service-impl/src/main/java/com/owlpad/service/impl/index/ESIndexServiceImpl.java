package com.owlpad.service.impl.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import com.owlpad.domain.index.IndexRequest;
import com.owlpad.domain.index.IndexResponse;
import com.owlpad.service.impl.esclient.ESSingletonClient;
import com.owlpad.service.index.IndexService;

public class ESIndexServiceImpl implements IndexService {
	private static final Logger logger = LoggerFactory.getLogger(ESIndexServiceImpl.class);

	@Override
	public IndexResponse index(IndexRequest indexRequest) {
		IndexResponse response = new IndexResponse();
		String suffix = indexRequest.getSuffix();
		String dataDirPath = indexRequest.getDirectoryPath();
		File dataDir = new File(dataDirPath);

		try {
			response.setDocumentsIndexed(indexDir(dataDir, suffix));
		} catch (IOException e) {
			logger.info("Exception while calling index.  Exception"+ e);
		}

		return response;
	}

	/**
	 * Method to index a directory by passing the directory to index, location
	 * to store the index, and word endings.
	 * 
	 * @param indexDir
	 * @param dataDir
	 * @param suffix
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private int indexDir(File dataDir, String suffix) throws IOException {
		Client client = ESSingletonClient.INSTANCE;
		try {
			CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices().prepareCreate("owlpad-index");
			createIndexRequestBuilder.execute().actionGet();
		} catch (IndexAlreadyExistsException e) {
			logger.info("Could not create index because it exists already.");
		}

		BulkRequestBuilder bulkRequest = client.prepareBulk();

		List<File> filesToIndex = new ArrayList<>();
		getFiles(dataDir, filesToIndex);
		indexFileWithIndexWriter(client, bulkRequest, filesToIndex, suffix);
		if (filesToIndex.size() > 0) {
			bulkRequest.execute().actionGet();
		}

		client.close();
		return filesToIndex.size();
	}

	/**
	 * Method to index a directory recursively.
	 * 
	 * @param indexWriter
	 * @param dataDir
	 * @param suffix
	 * @throws IOException
	 */
	private void getFiles(File dataDir, List<File> filesToIndex) {
		File[] files = dataDir.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				getFiles(f, filesToIndex);
			} else {
				filesToIndex.add(f);
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
	private void indexFileWithIndexWriter(Client client,BulkRequestBuilder bulkRequest, List<File> filesToIndex,String suffix) throws IOException {
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

					bulkRequest.add(client.prepareIndex("owlpad-index", "docs",String.valueOf(num))
							.setSource(jsonBuilder()
									.startObject()
									.field("contents", sb.toString())
									.field("filepath",f.getCanonicalPath())
									.field("filename", f.getName())
									.endObject()));
					num++;

				} catch (IOException e) {
					logger.info("Exception while calling indexFileWithIndexWriter: "+ e);
				}
			}
		}
	}
}
