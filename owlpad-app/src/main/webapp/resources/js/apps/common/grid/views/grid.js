/**
 * Represents a requirejs module for a dynamic Grid or Table
 */

define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'apps/common/grid/views/gridRow', 
         'hbs!apps/common/grid/templates/gridTemplate' ],
         function($, _, Backbone, Marionette, GridRow, gridTemplate) {

			var Grid = Backbone.Marionette.CompositeView.extend({
				template : gridTemplate,
				tagName : "table",
				className: "table table-bordered table-striped",
				itemView : GridRow,
				
				initialize : function(options) {
					this.model = new Backbone.Model();
					this.model.collection = options.collection;
					if (options.headers) {
						this.model.set('headers', options.headers);
					}
					
					this.listenTo(this.model.collection,"app:grid:showPreview",this.showPreview);
				},
				
				showPreview: function(data){
				  this.trigger('app:gc:showPreview',data);
				},

				appendHtml : function(collectionView, itemView) {
					collectionView.$("tbody").append(itemView.el);
				}
			});

			return Grid;
		});