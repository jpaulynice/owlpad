/**
 * Main controller manages all controllers and main application responsibilities.
 * 
 */
define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'apps/search/views/header/header'], 
         function($, _,Backbone, Marionette,HeaderView) {

    var HeaderController = Backbone.Marionette.Controller.extend({
        initialize : function(headerRegion) {            
            var headerView = new HeaderView();
            headerRegion.show(headerView);

            this.listenTo(headerView,'app:view:search',this.search);
        },
        
        search: function(data){
            this.trigger('app:header:search',data);
        }
    });

    return HeaderController;
});