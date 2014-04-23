/**
 * Simple controller to manage our views and models
 * 
 */
define([
        'jquery',
        'underscore',
        'backbone',
        'marionette'
    ], function($,_,Backbone,Marionette) {

	SearchAppController = Backbone.Marionette.Controller.extend({
		initialize: function(options){
			
		}
		
	});
    
    return SearchAppController;
});