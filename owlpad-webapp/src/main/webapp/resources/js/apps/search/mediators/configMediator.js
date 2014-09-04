/**
 * AppMediator manages all controllers and main application responsibilities.
 *
 */
define(['jquery', 
        'underscore', 
        'backbone', 
        'marionette', 
        'apps/search/controllers/configController'], 
        function($, _, Backbone, Marionette,ConfigController){

    var ConfigMediator = Backbone.Marionette.Controller.extend({

        initialize : function() {
            this.configController = new ConfigController();
        },
        
        loadConfig: function(){
            var self = this;
            this.config;
            var call = self.configController.loadConfig();
            $.when(call).done(function(results) {
                self.getConfig(results);
            }); 
        },
        
        getConfig: function(data){
            return data;
        }
 
    });

    return ConfigMediator;
}); 