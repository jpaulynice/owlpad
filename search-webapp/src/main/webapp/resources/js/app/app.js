/**
 * Modularized Backbone.Marionette Application
 */
define([ 'jquery', 'underscore', 'backbone', 'marionette',
		'app/controllers/controller' ], function($, _, Backbone, Marionette,
		SearchController) {

	var SearchApp = new Backbone.Marionette.Application();

	SearchApp.addRegions({
		content : '.content'
	});

	var searchController = new SearchController(SearchApp.content);
	//search for "java" on startup and return first 20 results.
	searchController.search({
		keyWord : 'java',
		maxHits : 20
	});

	return SearchApp;
});