/**
 * Modularized Backbone.Marionette Application
 */
define([
        'jquery',
        'underscore',
        'backbone',
        'marionette',
        'app/controllers/controller'
    ], function($,_,Backbone,Marionette, SearchController) {

	SearchApp = new Backbone.Marionette.Application();

    SearchApp.addRegions({
    	header: ".navbar-fixed-top",
    	sideBar: ".sidebarNav",
    	mainRegion : ".page-wrapper"
    });
    
    var searchController = new SearchController();
    searchController.search({keyWord:'java',maxHits:20});
    
    return SearchApp;
});