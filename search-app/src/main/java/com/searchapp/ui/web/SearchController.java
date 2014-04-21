package com.searchapp.ui.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.searchapp.search.domain.SearchRequest;
import com.searchapp.search.domain.SearchResponse;
import com.searchapp.ui.repository.SearchRepository;

/**
 * Handles request for search
 * 
 * @author Jay Paulynice
 *
 */
@Controller
@RequestMapping(value="search")
public class SearchController {
	private SearchRepository searchRepository;
	
	@Autowired
	public SearchController(SearchRepository searchRepository){
		this.searchRepository = searchRepository;
	}
	
	/**
	 * Search end point
	 * 
	 * @param searchRequest
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody SearchResponse search(@RequestBody SearchRequest searchRequest){
		return searchRepository.search(searchRequest);
	}
}