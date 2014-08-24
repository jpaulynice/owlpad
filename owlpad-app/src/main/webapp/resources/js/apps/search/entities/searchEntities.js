/**
 * Jquery/AJax calls to search
 * 
 * @param data
 * @returns ajax calls
 */
define([ 'jquery', 
         'json2', ], 
         function($, JSON) {

	var SearchEntities = function(data) {

		/**
		 * Call the search end point
		 * 
		 */

        this.search = function(data) {
            return $.ajax({
                method : "POST",
                data : JSON.stringify(data),
                dataType : "json",
                contentType : "application/json",
                url : "/owlpad/search/docs"
            });
        }; 

		
		/**
		 * Get a document by id
		 * 
		 */

        this.getDocById = function(docId) {
            return $.ajax({
                method : "GET",
                dataType : "json",
                contentType : "application/json",
                url : "/owlpad/search/docs/" + encodeURIComponent(docId)
            });
        }; 

	};

	return SearchEntities;
});