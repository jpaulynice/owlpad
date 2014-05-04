/**
 * Grid controller to handle showing search results
 * 
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'app/views/grid/grid'], 
		function($, _,Backbone, Marionette, Grid) {

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
				for(var key in data.documents[0].fields){
					headers.push(data.documents[0].fields[key].name);
				}
				
				var coll = new Backbone.Collection();
				
				for(var i in data.documents){
					var fields = data.documents[i].fields;
					coll.add(new Backbone.Model({fields:fields}));
				}

				var gridView = new Grid({
					collection : coll,
					headers : headers
				});
				this.gridRegion.show(gridView);
			}
		}

	});

	return GridController;
});