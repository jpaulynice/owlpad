/**
 * Defines a modularized row in our grid.
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'hbs!apps/common/grid/templates/gridRowTemplate' ], 
         function($, _, Backbone,Marionette, gridRowTemplate) {

	var GridRow = Backbone.Marionette.ItemView.extend({
		template : gridRowTemplate,
		tagName : "tr",
		
		events:{
		    "click": "handleClick"
		},
		
		handleClick: function(e){
		    this.model.trigger('app:grid:showPreview',this.model.get('source'));
		}
	});

	return GridRow;
});