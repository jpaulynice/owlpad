/**
 * Jquery/AJax calls to load configuration
 * 
 * @param data
 * @returns ajax calls
 */
define([ 'jquery', 
         'json2', ], 
         function($, JSON) {

	var ConfigEntities = function(data) {

        /**
         * Get a document by id
         *
         */
        this.loadConfig = function() {
            return $.ajax({
                method : "GET",
                dataType : "json",
                async: false,
                contentType : "application/json",
                url : "/owlpad/config"
            });
        }; 


	};

	return ConfigEntities;
});