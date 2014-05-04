/**
 * Represents a requirejs module for a dynamic Grid or Table
 */

define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'app/views/grid/gridRow', 
         'hbs!app/templates/grid/gridTemplate' ],
		function($, _, Backbone, Marionette, GridRow, gridTemplate) {

			var Grid = Backbone.Marionette.CompositeView.extend({
				template : gridTemplate,
				tagName : "table",
				className: "table",
				itemView : GridRow,

				events : {
					'mouseover tr' : 'highlightRow'
				},

				initialize : function(options) {
					this.model = new Backbone.Model();
					if (options.headers) {
						this.model.set('headers', options.headers);
					}
				},

				appendHtml : function(collectionView, itemView) {
					collectionView.$("tbody").append(itemView.el);
				},

				highlightRow : function(e) {

				}
			});

			return Grid;
		});