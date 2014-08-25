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
            this.searchController = new SearchController();
            var headerController = new HeaderController(regions.header);

            this.listenTo(headerController, 'app:header:search', this.search);
            this.listenTo(this.gridController, 'app:controller:preview', this.getDocById);
            this.listenTo(this.gridController, 'app:controller:page', this.handlePaging);
            
            var searchCriteria = {
                resultStart : 0,
                keyWord : "*",
                maxHits : 20
            };
            this.currentPage = 1;
            this.totalHits = 0;

            this.search(searchCriteria);
        },

        handlePaging : function(action) {
            var hitsPerpage = this.searchCriteria['maxHits'];
            var start = this.searchCriteria['resultStart'];

            if (action === 'first') {
                start = 0;
                this.currentPage = 1;
            } else if (action === 'prev') {
                this.currentPage--;
                start = hitsPerpage * (this.currentPage - 1);
            } else if (action === 'next') {
                start = hitsPerpage * this.currentPage;
                this.currentPage++;
            } else if (action === 'last') {
                var mod = this.totalHits % hitsPerpage;
                var counter = Math.floor(this.totalHits / hitsPerpage);
                this.currentPage = counter;

                if (mod === 0) {
                    start = this.totalHits - hitsPerpage;
                } else {
                    start = this.totalHits - mod;
                    this.currentPage++;
                }
            }
            this.searchCriteria['resultStart'] = start;
            this.search(this.searchCriteria);
        },

        search : function(data) {
            this.searchCriteria = data;
            var self = this;
            var searchCall = this.searchController.search(data);
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
            var call = this.searchController.getDocById(docId);
            $.when(call).done(function(results) {
                self.showPreview(results);
            });
        },

        showPreview : function(data) {
            if (data && data.source) {
                var view = new Preview({
                    model : new Backbone.Model({
                        source : data.source
                    })
                });
                this.preview.show(view);
            } else {
                this.preview.show(new EmptyView({
                    model : new Backbone.Model({
                        message : "No preview to show!"
                    })
                }));
            }
        }
    });

    return AppMediator;
}); 