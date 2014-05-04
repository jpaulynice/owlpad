/**
 * Main controller manages all controllers and main application responsibilities.
 * 
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'app/views/header/header' ,
         'app/controllers/searchController',
         'app/controllers/gridController'], 
         function($, _,Backbone, Marionette,HeaderView,SearchController,GridController) {

	var MainController = Backbone.Marionette.Controller.extend({
		initialize : function(options) {
			this.header = options.header;
			this.gridController = new GridController(options.gridRegion);
			this.searchController = new SearchController();
			
			var headerView = new HeaderView();
			this.listenTo(headerView,'searchapp:search',this.search);
			
			this.header.show(headerView);
		},
		
		search: function(data){
			var self = this;
			var searchCall = this.searchController.search(data);
			$.when(searchCall).done(function(results){
				self.showSearchResults(results);
			});
		},
		
		showSearchResults: function(data){
			this.gridController.showGridResults(data);
		}
	});

	return MainController;
});