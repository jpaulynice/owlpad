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
public class SearchServiceImpl implements SearchService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.searchapp.search.service.SearchService#search(com.searchapp.search
	 * .domain.SearchRequest)
	 */
	@Override
	public SearchResponse search(SearchRequest searchRequest) throws Exception {

		// will need to specify index directory for searching...this is just an
		// example since i have it already. For now the index is stored on disk
		// for small indexes an in-memory index will be ideal. Will fix later.
		// This index only contains files ending with ".java"
		File indexDir = new File("/Users/julespaulynice/Documents/search/index");
		Directory directory = FSDirectory.open(indexDir);

		String query = searchRequest.getKeyWord();
		int hits = searchRequest.getMaxHits();

		return searchIndex(directory, query, hits);

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
		QueryParser parser = new QueryParser(Version.LUCENE_47, "contents", new StandardAnalyzer(Version.LUCENE_47));

		Query query = parser.parse(queryStr);
		ScoreDoc[] hits = searcher.search(query, null, maxHits).scoreDocs;

		for (int i = 0; i < hits.length; i++) {
			int docId = hits[i].doc;
			int docPosition = i+1;
			Document docResult = getDocument(searcher, docId, docPosition);

			results.add(docResult);
		}
		response.setDocuments(results);

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

		fields.add(docIdField);
		fields.add(docNameField);
		fields.add(docPathField);

		docResult.setFields(fields);

		return docResult;
	}

}
