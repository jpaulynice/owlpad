/**
 * Modularized Backbone.Marionette Application
 */
define([
        'jquery',
        'underscore',
        'backbone',
        'marionette'
    ], function($,_,Backbone,Marionette) {

	SearchApp = new Backbone.Marionette.Application();

    SearchApp.addRegions({
    	header: ".navbar-fixed-top",
    	sideBar: ".sidebarNav",
    	mainRegion : ".page-wrapper"
    });
    
    return SearchApp;
});