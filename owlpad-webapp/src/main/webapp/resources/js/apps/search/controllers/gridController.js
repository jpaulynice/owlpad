/**
 * Grid controller to handle showing search results
 *
 */
define(['jquery', 
        'underscore', 
        'backbone', 
        'marionette', 
        'common/grid/views/grid', 
        'common/grid/views/empty'], 
        function($, _, Backbone, Marionette, Grid, EmptyView) {

    var GridController = Backbone.Marionette.Controller.extend({
        initialize : function(options) {
            this.gridRegion = options;
        },

        getHeaders : function(fields) {
            var headers = [];
            _.each(fields, function(field) {
                if (field.visible) {
                    headers.push(field.name);
                }
            });
            return headers;
        },

        getColumns : function(docs) {
            var coll = new Backbone.Collection();

            _.each(docs, function(doc) {
                var visibleFields = [];
                var docId = "";
                _.each(doc.fields, function(field) {
                    if (field.visible) {
                        visibleFields.push(field);
                    }

                    if (field.name === 'dId') {
                        docId = field.value;
                    }
                });
                coll.add(new Backbone.Model({
                    fields : visibleFields,
                    docId : docId
                }));
            });

            return coll;
        },

        /**
         * After results come back, we want to show the data in a grid
         */
        showGridResults : function(data) {
            if (data.documents && data.documents.length > 0) {
                var fields = data.documents[0].fields;
                var headers = this.getHeaders(fields);
                var columns = this.getColumns(data.documents);

                var gridView = new Grid({
                    collection : columns,
                    headers : headers
                });

                this.listenTo(gridView, 'app:grid:preview', this.showPreview);
                this.listenTo(gridView, 'app:grid:paging', this.paging);

                this.gridRegion.show(gridView);

                columns.models[0].set('highlighted', true);
                var pData = columns.models[0].get('docId');
                this.showPreview(pData);
            } else {
                this.showEmptyView();
            }
        },

        showEmptyView: function() {
            this.gridRegion.show(new EmptyView({
                model : new Backbone.Model({
                    message : "No documents found!"
                })
            }));
            this.showPreview();
        },

        paging : function(data) {
            this.trigger('app:controller:page', data);
        },

        showPreview : function(data) {
            this.trigger('app:controller:preview', data);
        }
    });

    return GridController;
});
