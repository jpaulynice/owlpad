/**
 * Application configuration with shortcut alias
 */
require.config({
    paths : {
        hbs : 'vendor/hbs',
        jquery : 'vendor/jquery',
        json2 : 'vendor/json2',
        underscore : 'vendor/underscore',
        handlebars : 'vendor/handlebars',
        backbone : 'vendor/backbone',
        marionette : 'vendor/backbone.marionette.min',
        i18nprecompile : 'vendor/i18nprecompile',
        bootstrap : 'vendor/bootstrap.min',
        syntaxHighlighter : 'vendor/syntaxHighlighter/shCore.min',
        shBrush : 'vendor/syntaxHighlighter/shBrushJava',
        'jasmine': 'vendor/jasmine',
        'jasmine-html': 'vendor/jasmine-html',
        spec: 'tests/spec'
    },

    shim : {
        jquery : {
            exports : '$'
        },
        json2 : {
            exports : 'JSON'
        },
        underscore : {
            exports : '_'
        },
        handlebars : {
            exports : 'Handlebars'
        },
        backbone : {
            deps : ['jquery', 'json2', 'underscore'],
            exports : 'Backbone'
        },
        marionette : {
            deps : ['jquery', 'underscore', 'backbone'],
            exports : 'Marionette'
        },
        bootstrap : {
            deps : ['jquery'],
            exports : 'Bootstrap'
        },
        syntaxHighlighter : {
            deps : ['jquery'],
            exports : 'SyntaxHighlighter'
        },
        shBrush : {
            deps : ['syntaxHighlighter'],
            exports : 'shBrush'
        },
        'jasmine': {
            exports: 'jasmine'
        },
        'jasmine-html': {
          deps: ['jasmine'],
          exports: 'jasmine'
        }
    }
}); 

require(['jquery', 
         'underscore', 
         'jasmine-html'], 
         function($, _, jasmine) {

    var jasmineEnv = jasmine.getEnv();
    jasmineEnv.updateInterval = 1000;
    specs = [];

    var htmlReporter = new jasmine.HtmlReporter();

    jasmineEnv.addReporter(htmlReporter);

    jasmineEnv.specFilter = function(spec) {
        return htmlReporter.specFilter(spec);
    };

    specs.push('spec/model');

    $(function() {
        require(specs, function() {
            jasmineEnv.execute();
        });
    });

}); 
