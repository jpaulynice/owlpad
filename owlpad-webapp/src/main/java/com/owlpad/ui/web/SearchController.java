package com.owlpad.ui.web;

import java.util.Locale;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.owlpad.domain.search.DocResponse;
import com.owlpad.domain.search.SearchRequest;
import com.owlpad.domain.search.SearchResponse;
import com.owlpad.ui.repository.SearchRepository;

/**
 * Handles request for search
 *
 * @author Jay Paulynice
 *
 */
@Controller
@RequestMapping(value = "search")
public class SearchController {
    private final SearchRepository repository;

    /**
     * @param repository the search repository
     */
    @Autowired
    public SearchController(final SearchRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns search page
     *
     * @param locale the user locale
     * @param model the model as a map
     * @return search page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String search(final Locale locale, final Model model) {
        return "search";
    }

    /**
     * Search documents with the requested criteria
     *
     * @param searchRequest the search request
     * @return search results matching the criteria
     */
    @RequestMapping(value = "docs", method = RequestMethod.POST)
    @ResponseBody
    public SearchResponse search(
            @Nonnull @RequestBody final SearchRequest searchRequest) {
        return repository.search(searchRequest);
    }

    /**
     * Search for a specific document matching the id
     *
     * @param docId the document id
     * @return document with the specified id
     */
    @RequestMapping(value = "docs/{docId}", method = RequestMethod.GET)
    @ResponseBody
    public DocResponse search(@Nonnull @PathVariable("docId") final String docId) {
        return repository.search(docId);
    }
}