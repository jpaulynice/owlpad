/**
 * Simple controller to manage our views and models
 * 
 */
define([ 'jquery', 'underscore', 'backbone', 'marionette',
		'app/entities/entities', 'app/views/grid/grid','app/views/header/header' ], function($, _,
		Backbone, Marionette, ajaxEntities, Grid,HeaderView) {

	var SearchAppController = Backbone.Marionette.Controller.extend({
		initialize : function(options) {
			this.entities = new ajaxEntities();
			this.header = options.header;
			this.content = options.content;
			
			var headerView = new HeaderView();
			this.listenTo(headerView,'searchapp:search',this.search);
			
			this.header.show(headerView);
		},

		/**
		 * Search end point to call search from the controller.
		 */
		search : function(data) {
			var self = this;
			var defer = $.Deferred();
			var searchCall = this.entities.search(data);

			$.when(searchCall).done(function(jsonRes) {
				self.showGridResuls(jsonRes);
				defer.resolve();
			}).fail(function() {
				defer.reject();
			});
			return defer.promise();
		},

		/**
		 * After results come back, we want to show the table data
		 */
		showGridResuls : function(data) {			
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
				this.content.show(gridView);
			}
		}

	});

	return SearchAppController;
});