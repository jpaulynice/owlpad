/**
 * Search controller to handle search
 * 
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'apps/search/entities/searchEntities'], 
         function($, _,Backbone, Marionette, SearchEntities) {

	var SearchAppController = Backbone.Marionette.Controller.extend({
		initialize : function(options) {
			this.searchEntities = new SearchEntities();
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
		},
		
		getDocById: function(docId){
            var defer = $.Deferred();
            var call = this.searchEntities.getDocById(docId);

            $.when(call).done(function(jsonRes) {
                defer.resolve(jsonRes);
            }).fail(function() {
                defer.reject();
            });
            
            return defer.promise();
        },
	});

	return SearchAppController;
});