/**
 * Jquery/AJax calls to search
 * 
 * @param data
 * @returns ajax calls
 */
define([ 'jquery', 
         'json2', ], 
         function($, JSON) {

    var Utils = function(data) {
        
        /**
         * Setup ajax load spinner
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
    };

    return Utils;
});