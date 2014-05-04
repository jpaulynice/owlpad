package com.searchapp.ui.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.searchapp.domain.index.IndexRequest;
import com.searchapp.domain.index.IndexResponse;
import com.searchapp.ui.repository.IndexRepository;

@Controller
@RequestMapping(value = "/index")
public class IndexController {
	private IndexRepository indexRepository;

	@Autowired
	public IndexController(IndexRepository indexRepository){
		this.indexRepository = indexRepository;
	}
	/**
	 * Index end point
	 * 
	 * @param indexRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody IndexResponse search(@RequestBody IndexRequest indexRequest){
		return indexRepository.index(indexRequest);
	}
}
