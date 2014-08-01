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
	     * Setup for ajax call to add/remove spinner
	     */
	    this.setup = function(){
	        $body = $("body");
            $(document).on({
                ajaxStart: function() { 
                    $body.addClass("loading");    
                },
                ajaxStop: function() {
                    $body.removeClass("loading"); 
                }    
            });
	    };

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
		
		/**
         * Call the search endpoint
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

	return SearchEntities;
});