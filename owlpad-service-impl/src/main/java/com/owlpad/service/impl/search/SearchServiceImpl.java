package com.owlpad.service.impl.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.owlpad.domain.search.Document;
import com.owlpad.domain.search.SearchRequest;
import com.owlpad.domain.search.SearchResponse;
import com.owlpad.domain.search.mapper.DocumentMapper;
import com.owlpad.service.search.SearchService;

/**
 * Apache Lucene {@link SearchService} implementation.
 *
 * @author Jay Paulynice
 *
 */
@Service
public class SearchServiceImpl implements SearchService {
    private static final Logger logger = LoggerFactory
            .getLogger(SearchServiceImpl.class);

    /*
     * (non-Javadoc)
     *
     * @see
     * com.owlpad.search.service.SearchService#search(com.owlpad.search.domain
     * .SearchRequest)
     */
    @Override
    public Response search(final SearchRequest searchRequest) {
        Preconditions.checkNotNull(searchRequest,
                "No search request specified.");

        final SearchResponse response = new SearchResponse();
        List<Document> docs;
        final String query = searchRequest.getKeyWord();
        final int hits = searchRequest.getHitsPerPage();

        final File indexDir = new File(
                "/Users/julespaulynice/Documents/luna/index");
        Directory directory = null;
        try {
            directory = FSDirectory.open(indexDir);
            docs = searchIndex(directory, query, hits);
            response.setDocuments(docs);
            response.setTotalDocuments(docs.size());
        } catch (final Exception e) {
            logger.info("Exception while calling search.  Exception: " + e);
            return Response.serverError().build();
        }
        final GenericEntity<SearchResponse> entity = new GenericEntity<SearchResponse>(
                response) {
        };
        return Response.ok(entity).build();
    }

    /**
     * Search the index for our query string and return only the hitsPerPage
     * count.
     *
     * @param indexDir
     * @param queryStr
     * @param hitsPerPage
     * @return
     * @throws Exception
     */
    private List<Document> searchIndex(final Directory indexDir,
            final String queryStr, final int hitsPerPage) throws Exception {

        final List<Document> results = new ArrayList<Document>();

        final DirectoryReader ireader = DirectoryReader.open(indexDir);
        final IndexSearcher searcher = new IndexSearcher(ireader);
        final QueryParser parser = new QueryParser(Version.LUCENE_48,
                "contents", new StandardAnalyzer(Version.LUCENE_48));

        final Query query = parser.parse(queryStr);
        final ScoreDoc[] hits = searcher.search(query, null, Integer.MAX_VALUE).scoreDocs;

        final int docsPerPage = hits.length < hitsPerPage ? hits.length
                : hitsPerPage;

        for (int i = 0; i < docsPerPage; i++) {
            final int docId = hits[i].doc;
            final int docPosition = i + 1;
            final org.apache.lucene.document.Document doc = searcher.doc(docId);
            final Document docResult = DocumentMapper.map(doc, docPosition);

            results.add(docResult);
        }

        return results;
    }

    @Override
    public Response search(final String docId) {
        // TODO: add implementation
        return null;
    }
}
