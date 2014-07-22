/**
 * Main controller manages all controllers and main application responsibilities.
 * 
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'app/views/header/header'], 
         function($, _,Backbone, Marionette,HeaderView) {

    var HeaderController = Backbone.Marionette.Controller.extend({
        initialize : function(headerRegion) {            
            var headerView = new HeaderView();
            headerRegion.show(headerView);

            this.listenTo(headerView,'searchapp:search',this.search);
        },
        
        search: function(data){
            this.trigger('searchapp:header:search',data);
        }
    });

    return HeaderController;
});