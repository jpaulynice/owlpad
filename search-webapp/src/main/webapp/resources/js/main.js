/**
 * Application configuration with shortcut alias
 */
require.config({
  paths: {
    jquery: 'vendor/jquery',
    json2: 'vendor/json2',
    underscore: 'vendor/underscore',
    handlebars: 'vendor/handlebars',
    backbone: 'vendor/backbone',
    marionette: 'vendor/backbone.marionette.min'
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
	    backbone : {
	      deps : ['jquery', 'json2', 'underscore'],
	      exports : 'Backbone'
	    },
	    marionette : {
	      deps : ['jquery', 'underscore', 'backbone'],
	      exports : 'Marionette'
	    }
	  }

});

require([
  'app',
], function(App){
  App.start();
});