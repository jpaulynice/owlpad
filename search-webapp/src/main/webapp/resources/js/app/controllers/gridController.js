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
			var headers = [];
			var coll = new Backbone.Collection();

			var gridView = new Grid({
				collection : coll,
				headers : headers
			});
			if(data.documents && data.documents.length>0){
				var headers = [];
				for(var key in data.documents[0].fields){
					headers.push(data.documents[0].fields[key].i18nKey);
				}
				
				var coll = new Backbone.Collection();
				
				for(var i in data.documents){
					var fields = data.documents[i].fields;
					coll.add(new Backbone.Model({fields:fields}));
				}

				gridView.collection = coll;
				gridView.headers = headers;
				this.gridRegion.show(gridView);
			}				
			this.gridRegion.show(gridView);
		}

	});

	return GridController;
});