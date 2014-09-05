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

        //wait for app config to load then 
        //show regions and execute search
        $.when(call).done(function(data) {
            var regions = data.configuration.layout;
            delete regions.id;
            App.addRegions(regions);

            var appRegions = {
                'header' : App.headerRegion,
                'gridRegion' : App.leftRegion,
                'preview' : App.rightRegion
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