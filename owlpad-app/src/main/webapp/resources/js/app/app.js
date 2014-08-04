/**
 * Modularized Backbone.Marionette Application
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'app/common/utils',
         'app/search/mediators/appMediator'], 
         function($, _, Backbone, Marionette,AppUtils,AppMediator) {

	var App = new Backbone.Marionette.Application();

	App.addRegions({
		header:".navbar-default",
		gridRegion : '.gridRegion'
	});
	
	var utils = new AppUtils();
	utils.setup();
	
	var appRegions = {'header':App.header,'gridRegion':App.gridRegion};

	var appMediator = new AppMediator(appRegions);

	return App;
});