package com.owlpad.service.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;

import com.owlpad.domain.search.Document;
import com.owlpad.domain.search.FacetResult;
import com.owlpad.domain.search.Facets;
import com.owlpad.domain.search.SearchResponse;
import com.owlpad.domain.search.mapper.DocumentMapper;

public class SearchResponseMapper {
    /**
     * Map from elastic search searchResponse to internal searchResponse
     *
     * @param response
     * @param from
     * @return
     */
    public static SearchResponse getInternalResponse(final org.elasticsearch.action.search.SearchResponse response,
            final int from) {
        final SearchHits hits = response.getHits();
        final SearchResponse res = new SearchResponse();

        final List<Document> docs = getDocumentsFromSearchHits(hits, from);
        final HashMap<String, Facets> facets = getFacetsFromAggregations(response.getAggregations());
        res.setDocuments(docs);
        res.setFacets(facets);
        res.setTotalDocuments(hits.getTotalHits());

        return res;
    }

    /**
     * Map from searchHits to Documents
     *
     * @param hits
     * @param from
     * @return
     */
    private static List<Document> getDocumentsFromSearchHits(final SearchHits hits, final int from) {
        final List<Document> docs = new ArrayList<Document>();

        int id = from + 1;
        for (final SearchHit hit : hits) {
            final Document doc = DocumentMapper.map(hit, id);
            docs.add(doc);
            id++;
        }

        return docs;
    }

    /**
     * Create facets from aggregations
     *
     * @param aggs
     * @return
     */
    private static HashMap<String, Facets> getFacetsFromAggregations(final Aggregations aggs) {
        final HashMap<String, Facets> facets = new HashMap<String, Facets>();
        for (final Aggregation ag : aggs) {
            final StringTerms st = (StringTerms) ag;
            final Facets f = getFacetResults(st.getBuckets());
            facets.put(ag.getName(), f);
        }
        return facets;
    }

    /**
     * Create facets result from aggregation term buckets.
     *
     * @param b
     * @return
     */
    private static Facets getFacetResults(final Collection<Bucket> buckets) {
        final Set<FacetResult> fres = new HashSet<>();
        for (final Bucket b : buckets) {
            final FacetResult f = new FacetResult();
            f.setCount(b.getDocCount());
            f.setEntry(b.getKey());
            fres.add(f);
        }
        return new Facets(fres);
    }
}
