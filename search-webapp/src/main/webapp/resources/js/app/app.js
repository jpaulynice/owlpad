/**
 * Modularized Backbone.Marionette Application
 */
define([ 'jquery', 'underscore', 'backbone', 'marionette',
		'app/controllers/controller' ], function($, _, Backbone, Marionette,
		SearchController) {

	var SearchApp = new Backbone.Marionette.Application();

	SearchApp.addRegions({
		header:".navbar-default",
		content : '.content'
	});
	
	var appRegions = {'header':SearchApp.header,'content':SearchApp.content};

	var searchController = new SearchController(appRegions);

	return SearchApp;
});