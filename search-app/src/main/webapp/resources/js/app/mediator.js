/**
 * SearchAppMediator serves as our data access layer.  Using jQuery promises
 * we can handle one or multiple ajax calls.
 * 
 */

SearchAppMediator = {
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
};