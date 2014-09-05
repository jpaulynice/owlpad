/**
 * Search controller to handle search
 *
 */
define(['jquery', 
        'underscore', 
        'backbone', 
        'marionette',
        'apps/search/entities/configEntities'], 
        function($, _, Backbone, Marionette, ConfigEntities) {

    var ConfigController = Backbone.Marionette.Controller.extend({
        initialize : function(options) {
            this.configEntities = new ConfigEntities();
        },
        
        loadConfig: function(){
            var defer = $.Deferred();
            var call = this.configEntities.loadConfig();

            $.when(call).done(function(jsonRes) {
                defer.resolve(jsonRes);
            }).fail(function() {
                defer.reject();
            });

            return defer.promise();
        }
    });

    return ConfigController;
}); 