package com.searchapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.searchapp.search.domain.SearchRequest;
import com.searchapp.search.domain.SearchResponse;

/**
 * {@link SearchController} is the entry point for search.
 * 
 * @author Jay Paulynice
 *
 */
@Controller
@RequestMapping(value="/searchApp")
public class SearchController {
		
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody SearchResponse search(@RequestBody SearchRequest searchRequest){
		return new SearchResponse();
	}

}
