/**
 * AppMediator manages all controllers and main application responsibilities.
 *
 */
define(['jquery', 
        'underscore', 
        'backbone', 
        'marionette', 
        'apps/search/controllers/headerController', 
        'apps/search/controllers/searchController', 
        'apps/search/controllers/gridController', 
        'apps/search/views/previewView', 
        'common/grid/views/empty'], 
        function($, _, Backbone, Marionette, HeaderController, SearchController, GridController, Preview, EmptyView) {

    var AppMediator = Backbone.Marionette.Controller.extend({

        initialize : function(regions) {
            this.preview = regions.preview;
            this.gridController = new GridController(regions.gridRegion);
            var headerController = new HeaderController(regions.header);
            this.searchController = new SearchController();

            this.listenTo(headerController, 'app:header:search', this.search);
            this.listenTo(this.gridController, 'app:controller:preview', this.getDocById);
            this.listenTo(this.gridController, 'app:controller:page', this.handlePaging);
            
            this.currentPage = 1;
            this.totalHits = 0;
        },

        //working on paging...not complete yet.
        handlePaging : function(action) {
            var searchCriteria = this.searchCriteria;
            var hitsPerpage = searchCriteria['hitsPerPage'];
            var start = searchCriteria['resultStart'];

            if (action == 'first') {
                start = 0;
                this.currentPage = 1;
            } else if (action == 'prev') {
                this.currentPage--;
                start = hitsPerpage * (this.currentPage - 1);
            } else if (action == 'next') {
                start = hitsPerpage * this.currentPage;
                this.currentPage++;
            } else if (action == 'last') {
                var mod = this.totalHits % hitsPerpage;
                this.currentPage = Math.floor(this.totalHits / hitsPerpage);

                if (mod === 0) {
                    start = this.totalHits - hitsPerpage;
                } else {
                    start = this.totalHits - mod;
                    this.currentPage++;
                }
            }
            searchCriteria['resultStart'] = start;
            this.search(searchCriteria);
        },

        search : function(data) {
            var self = this;
            self.searchCriteria = data;
            var searchCall = self.searchController.search(data);
            $.when(searchCall).done(function(results) {
                self.showSearchResults(results);
            });
        },

        showSearchResults : function(data) {
            this.totalHits = data.totalDocuments;
            this.gridController.showGridResults(data);
        },

        getDocById : function(docId) {
            var self = this;
            var call = self.searchController.getDocById(docId);
            $.when(call).done(function(results) {
                self.showPreview(results);
            });
        },

        showPreview : function(data) {
            var view = '';
            if (data && data.source) {
                view = new Preview({model : new Backbone.Model({source : data.source})});
            } else {
                view = new EmptyView({model : new Backbone.Model({message : "No preview to show!"})});
            }
            this.preview.show(view);
        }
    });

    return AppMediator;
}); 