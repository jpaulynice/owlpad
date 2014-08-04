/**
 * Grid controller to handle showing search results
 * 
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'app/common/grid/views/grid',
         'app/common/grid/views/empty'], 
         function($, _,Backbone, Marionette, Grid, EmptyView) {

	var GridController = Backbone.Marionette.Controller.extend({
		initialize : function(options) {
			this.gridRegion = options;
		},

		
		/**
		 * After results come back, we want to show the table data
		 */
		showGridResults : function(data) {	
			if(data.documents && data.documents.length>0){
				var headers = [];
				var fields = data.documents[0].fields;
				_.each(fields, function(field){
				    headers.push(field.name);
				});
				
				var coll = new Backbone.Collection();
				
				_.each(data.documents,function(doc){
				    coll.add(new Backbone.Model({fields:doc.fields}));
				});

				var gridView = new Grid({
					collection : coll,
					headers : headers
				});
				this.gridRegion.show(gridView);
			}else{
				this.gridRegion.show(new EmptyView());
			}
		}

	});

	return GridController;
});