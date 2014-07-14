package com.owlpad.service.impl.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import com.owlpad.domain.index.IndexRequest;
import com.owlpad.domain.index.IndexResponse;
import com.owlpad.service.index.IndexService;

public class ESIndexServiceImpl implements IndexService {
	private static final Logger logger = LoggerFactory.getLogger(ESIndexServiceImpl.class);

	 public static void main(String [] args){
		 ESIndexServiceImpl indService = new ESIndexServiceImpl(); 
		 IndexRequest request = new IndexRequest();
		 request.setDirectoryPath("/Users/julespaulynice/Documents/workspace");
		 request.setSuffix("java");
		 
		 indService.index(request);
	 }

	@Override
	public IndexResponse index(IndexRequest indexRequest) {
		IndexResponse response = new IndexResponse();
		String suffix = indexRequest.getSuffix();
		String dataDirPath = indexRequest.getDirectoryPath();
		File dataDir = new File(dataDirPath);

		response.setDocumentsIndexed(indexDir(dataDir, suffix));

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
	 * @throws Exception
	 */
	private int indexDir(File dataDir, String suffix) {
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("cluster.name", "elasticsearch").build();
		Client client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(
						"localhost", 9300));
		try{
			CreateIndexRequestBuilder createIndexRequestBuilder = client.admin()
					.indices().prepareCreate("owlpad");
			createIndexRequestBuilder.execute().actionGet();
		}catch(IndexAlreadyExistsException e){
			logger.info("Excpetion while calling indexDir: "+e);
		}

		IndexRequestBuilder indexRequestBuilder = client.prepareIndex("owlpad",
				"docs", "1");

		int numIndexed = indexDirectory(indexRequestBuilder, dataDir, suffix, 0);

		client.close();
		logger.debug("Documents indexed: "+numIndexed);
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
	private int indexDirectory(IndexRequestBuilder indexRequestBuilder,File dataDir, String suffix, int numIndexed) {

		File[] files = dataDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				indexDirectory(indexRequestBuilder, f, suffix, numIndexed);
			} else {
				try {
					indexFileWithIndexWriter(indexRequestBuilder, f, suffix);
					numIndexed++;
				} catch (IOException e) {
					logger.info("Excpetion while calling indexDirectory: "+e);
				}
			}
		}

		return numIndexed;

	}

	/**
	 * Index a file by creating a Document and adding fields
	 * 
	 * @param indexWriter
	 * @param f
	 * @param suffix
	 * @throws IOException
	 */
	private void indexFileWithIndexWriter(IndexRequestBuilder indexRequestBuilder, File f, String suffix) throws IOException {

		if (f.isHidden() || f.isDirectory() || !f.canRead() || !f.exists()) {
			return;
		}
		if (suffix != null && !f.getName().endsWith(suffix)) {
			return;
		}

		XContentBuilder builder = null;
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(f));

			String content = reader.readLine();
			while ((content != null)) {
				content += content;
			}
			reader.close();
			
			builder = jsonBuilder().startObject()
					.field("contents", content)
					.field("filepath", f.getCanonicalPath())
					.field("filename", f.getName()).endObject();

		} catch (IOException e) {
			logger.info("Excpetion while calling indexFileWithIndexWriter: "+e);
		}			

		logger.debug("Indexing file: "+f.getCanonicalPath());
		indexRequestBuilder.setSource(builder);
		indexRequestBuilder.execute().actionGet();
	}
}
