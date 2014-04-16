package com.searchapp.ui.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.searchapp.search.domain.SearchRequest;
import com.searchapp.search.domain.SearchResponse;
import com.searchapp.ui.repository.SearchRepository;

/**
 * Handles request for home page and application ajax calls to fetch and load data.
 * 
 * @author Jay Paulynice
 *
 */
@Controller
public class HomeController {
	private SearchRepository searchRepository;
	
	@Autowired
	public HomeController(SearchRepository searchRepository){
		this.searchRepository = searchRepository;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	/**
	 * Search end point
	 * 
	 * @param searchRequest
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody SearchResponse search(@RequestBody SearchRequest searchRequest){
		return searchRepository.search(searchRequest);
	}
}
