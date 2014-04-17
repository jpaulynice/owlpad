package com.searchapp.search.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.searchapp.search.domain.Document;
import com.searchapp.search.domain.SearchRequest;
import com.searchapp.search.domain.SearchResponse;
import com.searchapp.search.service.SearchService;

import org.apache.lucene.util.QueryBuilder;
import org.apache.lucene.util.Version;

/**
 * {@link SearchService} implementation.
 * 
 * @author Jay Paulynice
 * 
 */
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
		// example since i have it already.
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

		IndexReader indexReader = DirectoryReader.open(indexDir);

		IndexSearcher searcher = new IndexSearcher(indexReader);

		QueryBuilder queryBuilder = new QueryBuilder(new StandardAnalyzer(
				Version.LUCENE_47));
		Query query = queryBuilder.createPhraseQuery("contents", queryStr);

		TopDocs topDocs = searcher.search(query, maxHits);

		ScoreDoc[] hits = topDocs.scoreDocs;

		for (int i = 0; i < hits.length; i++) {
			int docId = hits[i].doc;
			org.apache.lucene.document.Document doc = searcher.doc(docId);

			Document d = new Document();
			d.setDocId(docId);
			d.setDocTitle(doc.get("filename"));
			results.add(d);
		}
		response.setDocuments(results);

		return response;
	}

}
