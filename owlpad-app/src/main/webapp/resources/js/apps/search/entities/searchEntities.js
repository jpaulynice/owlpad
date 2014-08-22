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
		
		this.getDocById = function(data){
		    return $.ajax({
                method : "GET",
                dataType : "json",
                contentType : "application/json",
                url : "/owlpad/search/"+encodeURIComponent(data)
            });
		};
	};

	return SearchEntities;
});