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
         'apps/search/controllers/gridController',
         'apps/search/views/previewView'], 
         function($, _,Backbone, Marionette,HeaderController,SearchController,GridController,Preview) {

    var AppMediator = Backbone.Marionette.Controller.extend({
        initialize : function(regions) {
            this.preview = regions.preview;
            this.gridController = new GridController(regions.gridRegion);
            this.searchController = new SearchController();
            var headerController = new HeaderController(regions.header);
            
            this.listenTo(headerController,'searchapp:header:search',this.search);
            this.listenTo(this.gridController,"app:am:showPreview",this.getDocById);
            
            this.search({keyWord:"*",maxHits:10});
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
        },
        
        getDocById: function(docId){
            var self = this;
            var call = this.searchController.getDocById(docId);
            $.when(call).done(function(results){
                self.showPreview(results);
            });
        },
        
        showPreview: function(data){
            var view = new Preview({
              model:new Backbone.Model({source:data.source})
            });
            this.preview.show(view);
        }
    });

    return AppMediator;
});