/**
 * Grid controller to handle showing search results
 * 
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'common/grid/views/grid',
         'common/grid/views/empty'], 
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
				    if(field.visible){
				        headers.push(field.name);
				    }
				});
				
				
				var coll = new Backbone.Collection();
				
				_.each(data.documents,function(doc){
				    var visibleFields = [];
				    var docId = "";
				    _.each(doc.fields, function(field){
				        if(field.visible){
				            visibleFields.push(field);
				        }
				        
				        if(field.name === 'dId'){
				            docId = field.value;
				        }
				    });
				    coll.add(new Backbone.Model({fields:visibleFields,docId:docId}));
				});

				var gridView = new Grid({
					collection : coll,
					headers : headers
				});
				this.listenTo(gridView,'app:gc:showPreview',this.showPreview);
				this.gridRegion.show(gridView);
			}else{
				this.gridRegion.show(new EmptyView());
			}
		},
		
		showPreview: function(data){
		    this.trigger('app:am:showPreview',data);
		}
	});

	return GridController;
});