/**
 * Represents a requirejs module for a dynamic Grid or Table
 */

define(['jquery', 
        'underscore', 
        'backbone', 
        'marionette', 
        'common/grid/views/gridRow', 
        'hbs!common/grid/templates/gridTemplate'], 
        function($, _, Backbone, Marionette, GridRow, gridTemplate) {

    var Grid = Backbone.Marionette.CompositeView.extend({
        template : gridTemplate,
        tagName : "table",
        className : "table",
        itemView : GridRow,

        events : {
            "click .paging" : "handlePaging"
        },

        initialize : function(options) {
            this.model = new Backbone.Model();
            this.model.collection = options.collection;
            if (options.headers) {
                this.model.set('headers', options.headers);
            }

            this.listenTo(this.model.collection, "app:gridRow:preview", this.showPreview);
        },

        handlePaging : function(e) {
            var action = $(e.currentTarget).data("paging");
            this.trigger("app:grid:paging", action);
        },

        showPreview : function(data) {
            this.trigger('app:grid:preview', data);
        },

        appendHtml : function(collectionView, itemView) {
            collectionView.$("tbody").append(itemView.el);
        }
    });

    return Grid;
}); 