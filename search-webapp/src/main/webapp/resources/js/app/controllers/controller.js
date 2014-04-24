/**
 * Simple controller to manage our views and models
 * 
 */
define([ 'jquery', 'underscore', 'backbone', 'marionette',
		'app/entities/entities', 'app/views/grid/grid' ], function($, _,
		Backbone, Marionette, ajaxEntities, Grid) {

	SearchAppController = Backbone.Marionette.Controller.extend({
		initialize : function(options) {
			this.entities = new ajaxEntities();
			this.content = options;
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
			var headers = null;
			if (data.documents && data.documents[0]) {
				headers = _.keys(data.documents[0]);
			}

			var tableData = new Backbone.Collection({});
			for ( var key in data.documents) {
				var modelData = data.documents[key];
				tableData.add(new Backbone.Model({
					'tableRow' : modelData
				}));
			}

			var gridView = new Grid({
				collection : tableData,
				headers : headers
			});
			this.content.show(gridView);
		}

	});

	return SearchAppController;
});