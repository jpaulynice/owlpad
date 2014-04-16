/**
 * SearchAppMediator serves as our data access layer.  Using jQuery promises
 * we can handle one or multiple ajax calls.
 * 
 */

SearchAppMediator = {
		initialize: function(){
			this.entities = new ajaxEntities();
		}
		
}