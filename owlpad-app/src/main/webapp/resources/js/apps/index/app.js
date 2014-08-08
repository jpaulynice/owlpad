/**
 * Modularized Backbone.Marionette Application
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette'], 
         function($, _, Backbone, Marionette) {

	var App = new Backbone.Marionette.Application();

	return App;
});