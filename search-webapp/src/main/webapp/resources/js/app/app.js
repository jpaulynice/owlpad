/**
 * Modularized Backbone.Marionette Application
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
		'app/controllers/mainController' ], 
		function($, _, Backbone, Marionette,MainController) {

	var SearchApp = new Backbone.Marionette.Application();

	SearchApp.addRegions({
		header:".navbar-default",
		gridRegion : '.gridRegion'
	});
	
	var appRegions = {'header':SearchApp.header,'gridRegion':SearchApp.gridRegion};

	var mainController = new MainController(appRegions);

	return SearchApp;
});