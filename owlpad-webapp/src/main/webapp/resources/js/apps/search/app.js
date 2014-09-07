/**
 * Modularized Backbone.Marionette Application
 */
define(['jquery', 
        'underscore', 
        'backbone', 
        'marionette', 
        'common/utils', 
        'apps/search/mediators/searchMediator',
        'apps/search/mediators/configMediator'], 
        function($, _, Backbone, Marionette, AppUtils, SearchMediator, ConfigMediator) {

    var App = new Backbone.Marionette.Application();

    App.addInitializer(function() {
        var utils = new AppUtils();
        utils.setup();
        var configMediator = new ConfigMediator();
        var call = configMediator.loadConfig();

        $.when(call).done(function(data) {
            var reg = data.configuration.layout.regions;
            var regions = _.object(_.map(reg, function(region) {
               return [region.name, region.selector];
            }));
            
            App.addRegions(regions);

            var appRegions = {
                'header' : App.header,
                'r1' : App.r1,
                'r2' : App.r2
            };

            var searchMediator = new SearchMediator(appRegions);

            //kick off a "*" search on app startup.
            var searchCriteria = {
                resultStart : 0,
                keyWord : "*",
                hitsPerPage : 20
            };
            searchMediator.search(searchCriteria);
        });
    }); 

    return App;
}); 