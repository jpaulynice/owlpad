/**
 * Modularized Backbone.Marionette Application
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'app/mediators/appMediator' ], 
         function($, _, Backbone, Marionette,AppMediator) {

	var SearchApp = new Backbone.Marionette.Application();

	SearchApp.addRegions({
		header:".navbar-default",
		gridRegion : '.gridRegion'
	});
	
	var appRegions = {'header':SearchApp.header,'gridRegion':SearchApp.gridRegion};

	var appMediator = new AppMediator(appRegions);

	return SearchApp;
});