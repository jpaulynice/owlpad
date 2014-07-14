package com.owlpad.service.impl.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.owlpad.domain.search.Document;
import com.owlpad.domain.search.Field;
import com.owlpad.domain.search.SearchRequest;
import com.owlpad.domain.search.SearchResponse;
import com.owlpad.service.search.SearchService;

import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;

/**
 * {@link SearchService} implementation.
 * 
 * @author Jay Paulynice
 * 
 */
@Service("searchService")
public class SearchServiceImpl implements SearchService{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.owlpad.search.service.SearchService#search(com.owlpad.search.domain.SearchRequest)
	 */
	@Override
	public SearchResponse search(SearchRequest searchRequest){
		String query = searchRequest.getKeyWord();
		int hits = searchRequest.getMaxHits();
		
		File indexDir = new File("/Users/julespaulynice/Documents/search/index");
		Directory directory = null;
		try {
			directory = FSDirectory.open(indexDir);
			return searchIndex(directory, query, hits);
		} 
		catch (Exception e) {
			//logger
		}
		return null;

	}

	/**
	 * Search the index for our query string and return only the maxHits count.
	 * 
	 * @param indexDir
	 * @param queryStr
	 * @param maxHits
	 * @return
	 * @throws Exception
	 */
	private SearchResponse searchIndex(Directory indexDir, String queryStr,
			int maxHits) throws Exception {

		SearchResponse response = new SearchResponse();
		List<Document> results = new ArrayList<Document>();

		DirectoryReader ireader = DirectoryReader.open(indexDir);
		IndexSearcher searcher = new IndexSearcher(ireader);
		QueryParser parser = new QueryParser(Version.LUCENE_48, "contents", new StandardAnalyzer(Version.LUCENE_48));

		Query query = parser.parse(queryStr);
		ScoreDoc[] hits = searcher.search(query, null, Integer.MAX_VALUE).scoreDocs;
		
		int docsPerPage = hits.length < maxHits ? hits.length : maxHits;

		for (int i = 0; i < docsPerPage; i++) {
			int docId = hits[i].doc;
			int docPosition = i+1;
			Document docResult = getDocument(searcher, docId, docPosition);

			results.add(docResult);
		}
		response.setDocuments(results);
		response.setTotalDocuments(hits.length);

		return response;
	}

	/**
	 * Method to get document from searcher by docId
	 * 
	 * @param searcher
	 * @param docId
	 * @return
	 * @throws IOException
	 */
	private Document getDocument(IndexSearcher searcher, int docId, int docPosition) throws IOException{
		List<Field> fields = new ArrayList<Field>();
		org.apache.lucene.document.Document doc = searcher.doc(docId);
		Document docResult = new Document();
		Field docIdField = new Field();
		docIdField.setFieldId("doc_id");
		docIdField.setName("ID");
		docIdField.setI18nKey("searchapp.docId");
		docIdField.setFieldType("string");
		docIdField.setValue(String.valueOf(docPosition));

		Field docNameField = new Field();
		docNameField.setFieldId("doc_title");
		docNameField.setName("Title");
		docNameField.setI18nKey("searchapp.docTitle");
		docNameField.setFieldType("string");
		docNameField.setValue(doc.get("filename"));

		Field docPathField = new Field();
		docPathField.setFieldId("doc_path");
		docPathField.setName("URI");
		docPathField.setI18nKey("searchapp.docURI");
		docPathField.setFieldType("string");
		docPathField.setValue(doc.get("filepath"));
		
		Field authorField = new Field();
		authorField.setFieldId("doc_author");
		authorField.setName("Author");
		authorField.setI18nKey("searchapp.docAuthor");
		authorField.setFieldType("string");
		authorField.setValue(doc.get("author"));
		
		Field dateField = new Field();
		dateField.setFieldId("doc_date");
		dateField.setName("Last Modified");
		dateField.setI18nKey("searchapp.docDate");
		dateField.setFieldType("string");
		dateField.setValue(doc.get("lastModified"));
		
		Field created = new Field();
		created.setFieldId("doc_created");
		created.setName("Created");
		created.setI18nKey("searchapp.docCreated");
		created.setFieldType("string");
		created.setValue(doc.get("created"));
		
		Field sizeField = new Field();
		sizeField.setFieldId("doc_size");
		sizeField.setName("Size (Bytes)");
		sizeField.setI18nKey("searchapp.size");
		sizeField.setFieldType("string");
		sizeField.setValue(doc.get("size"));

		fields.add(docIdField);
		fields.add(docNameField);
		fields.add(docPathField);
		fields.add(authorField);
		fields.add(dateField);
		fields.add(created);
		fields.add(sizeField);

		docResult.setFields(fields);

		return docResult;
	}

}
