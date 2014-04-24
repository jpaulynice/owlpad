/**
 * Defines a modularized row in our grid.
 */
define([
        'jquery',
        'underscore',
        'backbone',
        'marionette',
        'handlebars'
    ], function($,_,Backbone,Marionette,Handlebars) {

	GridRow = Backbone.Marionette.ItemView.extend({
		template: Handlebars.compile($("#rowView").html()),
	    tagName: "tr"
	});
    
    return GridRow;
});