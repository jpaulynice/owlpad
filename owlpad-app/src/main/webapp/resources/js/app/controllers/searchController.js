/**
 * Search controller to handle search
 * 
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'app/entities/searchEntities'], 
         function($, _,Backbone, Marionette, SearchEntities) {

	var SearchAppController = Backbone.Marionette.Controller.extend({
		initialize : function(options) {
			this.searchEntities = new SearchEntities();
			this.searchEntities.setup();
		},

		/**
		 * Search end point to call search from the controller.
		 */
		search : function(data) {
			var defer = $.Deferred();
			var searchCall = this.searchEntities.search(data);

			$.when(searchCall).done(function(jsonRes) {
				defer.resolve(jsonRes);
			}).fail(function() {
				defer.reject();
			});
			return defer.promise();
		}
	});

	return SearchAppController;
});