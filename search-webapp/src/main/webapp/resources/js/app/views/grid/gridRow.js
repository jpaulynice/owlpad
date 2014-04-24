/**
 * Defines a modularized row in our grid.
 */
define([
        'jquery',
        'underscore',
        'backbone',
        'marionette',
        'handlebars',
        'hbs!templates/grid/gridRowTemplate'
    ], function($,_,Backbone,Marionette,Handlebars, gridRow) {

	GridRow = Backbone.Marionette.ItemView.extend({
		template: gridRow,
	    tagName: "tr"
	});
    
    return GridRow;
});