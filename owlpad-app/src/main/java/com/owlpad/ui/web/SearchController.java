package com.owlpad.ui.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping(value = "/search")
public class SearchController {
	private SearchRepository searchRepository;
	
	@Autowired
	public SearchController(SearchRepository searchRepository){
		this.searchRepository = searchRepository;
	}
	
	/**
	 * Returns search page
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String search(Locale locale, Model model) {
		return "search";
	}
	
	/**
	 * Search end point
	 * 
	 * @param searchRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody SearchResponse search(@RequestBody SearchRequest searchRequest){
		return searchRepository.search(searchRequest);
	}
}