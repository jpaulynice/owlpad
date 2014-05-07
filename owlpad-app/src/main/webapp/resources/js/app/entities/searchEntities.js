/**
 * Ajax entities used to communicate with the server to get and send data.
 * 
 * @param data
 * @returns ajax calls
 */
define([ 'jquery', 
         'json2', ], 
         function($, JSON) {

	var SearchEntities = function(data) {

		/**
		 * Call the search endpoint
		 */
		this.search = function(data) {
			return $.ajax({
				method : "POST",
				data : JSON.stringify(data),
				dataType : "json",
				contentType : "application/json",
				url : "/owlpad/search"
			});
		};
	};

	return SearchEntities;
});