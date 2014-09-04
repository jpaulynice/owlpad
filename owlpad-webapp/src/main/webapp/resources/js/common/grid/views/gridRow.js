/**
 * Defines a modularized row in our grid.
 */
define(['jquery', 
        'underscore', 
        'backbone', 
        'marionette', 
        'hbs!common/grid/templates/gridRowTemplate'], 
        function($, _, Backbone, Marionette, gridRowTemplate) {

    var GridRow = Backbone.Marionette.ItemView.extend({
        template : gridRowTemplate,
        tagName : "tr",

        events : {
            "click" : "highlightRow"
        },

        initialize : function(options) {
            _.bindAll(this, 'render');
            this.listenTo(this.model, 'change reset', this.render);
        },

        onRender : function() {
            if (this.model.get('highlighted') && !this.$el.hasClass('highlighted')) {
                this.$el.addClass('highlighted');
            } else if (this.$el.hasClass('highlighted')) {
                this.$el.removeClass('highlighted');
            }
        },

        highlightRow : function(e) {
            if (!this.model.get('highlighted')) {
                var models = this.model.collection.where({
                    highlighted : true
                });
                if (models.length > 0) {
                    var foundModel = models[0];
                    foundModel.set('highlighted', false);
                    this.model.set('highlighted', true);
                    this.model.trigger('app:gridRow:preview', this.model.get('docId'));
                }
            }
        }


    });

    return GridRow;
}); 