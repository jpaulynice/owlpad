/**
 * Backbone.Marionette application
 * 
 */

SearchApp = new Backbone.Marionette.Application();

SearchApp.addRegions({
	header: ".navbar-fixed-top",
	sideBar: ".sidebarNav",
	mainRegion : ".page-wrapper"
});

SearchApp.start();