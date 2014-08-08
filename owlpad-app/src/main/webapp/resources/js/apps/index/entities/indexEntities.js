/**
 * Jquery/AJax calls to index
 * 
 * @param data
 * @returns ajax calls
 */
define([ 'jquery', 
         'json2', ], 
         function($, JSON) {

	var IndexEntities = function(data) {
		
		/**
         * Call the index endpoint
         */
        this.index = function(data) {
            return $.ajax({
                method : "POST",
                data : JSON.stringify(data),
                dataType : "json",
                contentType : "application/json",
                url : "/owlpad/index"
            });
        };
	};

	return IndexEntities;
});