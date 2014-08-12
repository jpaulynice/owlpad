define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'bootstrap',
         'hbs!apps/search/templates/previewTemplate',
         'syntaxHighlighter',
         'shBrush'],
         function($, _, Backbone, Marionette,Bootstrap,template,SyntaxHighlighter, Brush) {
    
    var preview = Backbone.Marionette.ItemView.extend({
        template:template,
        
        onShow: function(){
            SyntaxHighlighter.highlight({
                toolbar: false,
                gutter: true
            });
        }
    });

    return preview;
});