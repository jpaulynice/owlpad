
/**
 * Modularized event bus for our application.
 */
define([
        'jquery',
        'underscore',
        'backbone'
    ], function($,_,Backbone) {

	var EventBus = _.extend({}, Backbone.Events);
    
    return EventBus;
});