/**
 * Simple controller to manage our views and models
 * 
 */
define([
        'jquery',
        'underscore',
        'backbone',
        'marionette',
        'app/entities/entities'
    ], function($,_,Backbone,Marionette,ajaxEntities) {

	SearchAppController = Backbone.Marionette.Controller.extend({
		initialize: function(options){
			this.entities = new ajaxEntities();
		},
		
		search: function(data){
			var defer = $.Deferred();
			var searchCall = this.entities.search(data);
			
			$.when(searchCall).done(function(jsonRes){
				defer.resolve();
				//fire event to say done
			}).fail(function(){
				defer.reject();
			});
			defer.promise();
		}
		
	});
    
    return SearchAppController;
});