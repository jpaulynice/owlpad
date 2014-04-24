/**
 * Represents a requirejs module for a dynamic Grid or Table
 */

define([
        'jquery',
        'underscore',
        'backbone',
        'marionette',
        'handlebars',
        'app/views/grid/gridRow'
    ], function($,_,Backbone,Marionette,Handlebars,GridRow) {

	var Grid = Backbone.Marionette.CompositeView.extend({
		template: Handlebars.compile($("#gridView").html()),
		tagName: "table",
	    itemView: GridRow,
	    
	    events: {
	    	'mouseover tr': 'highlightRow'
	    },
	    
	    initialize: function(options){
	    	this.model = new Backbone.Model();
	    	if(options.headers){
	        	this.model.set('headers',options.headers);	
	    	}
	    },
	    
	    appendHtml: function(collectionView, itemView){
	        collectionView.$("tbody").append(itemView.el);
	    },
	    
	    highlightRow: function(e){
	    	
	    }
	});
    
    return Grid;
});