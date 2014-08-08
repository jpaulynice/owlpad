/**
 * AppMediator manages all controllers and main application responsibilities.
 * 
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'apps/search/controllers/headerController' ,
         'apps/search/controllers/searchController',
         'apps/search/controllers/gridController'], 
         function($, _,Backbone, Marionette,HeaderController,SearchController,GridController) {

    var AppMediator = Backbone.Marionette.Controller.extend({
        initialize : function(regions) {
            this.gridController = new GridController(regions.gridRegion);
            this.searchController = new SearchController();
            var headerController = new HeaderController(regions.header);
            
            this.listenTo(headerController,'searchapp:header:search',this.search);
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

    return AppMediator;
});