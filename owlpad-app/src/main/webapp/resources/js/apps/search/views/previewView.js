define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'bootstrap',
         'hbs!apps/search/templates/previewTemplate' ],
         function($, _, Backbone, Marionette,Bootstrap, template) {
    
    var preview = Backbone.Marionette.ItemView.extend({
        template:template,
    });

    return preview;
});