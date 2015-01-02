package com.owlpad.ui.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.owlpad.domain.index.IndexRequest;
import com.owlpad.domain.index.IndexResponse;
import com.owlpad.ui.repository.IndexRepository;

/**
 * {@link IndexController} is used to get the index page as well as REST end
 * point for indexing a directory.
 *
 * @author Jay Paulynice
 *
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {
    private final IndexRepository repository;

    /**
     * @param repository
     *            the index handler
     */
    @Autowired
    public IndexController(final IndexRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns index page
     *
     * @param locale
     *            the user locale
     * @param model
     *            the model as a map
     * @return index page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(final Locale locale, final Model model) {
        return "index";
    }

    /**
     * Index end point. Takes a {@code IndexRequest} object and return the
     * number of files indexed as part of the response
     *
     * @param indexRequest
     *            request object
     * @return {@link IndexResponse} object
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public IndexResponse index(@RequestBody final IndexRequest indexRequest) {
        return repository.index(indexRequest);
    }
}
