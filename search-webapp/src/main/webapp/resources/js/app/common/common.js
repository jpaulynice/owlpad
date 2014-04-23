
/**
 * Modularized event bus for our application.
 */
define([
        'jquery',
        'underscore',
        'backbone'
    ], function($,_,Backbone) {

	EventManager = _.extend({}, Backbone.Events);
    
    return EventManager;
});