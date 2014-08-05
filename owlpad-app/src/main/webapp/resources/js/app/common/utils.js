/**
 * Utilities for various jquery functions.
 * 
 * @param data
 * @returns ajax calls
 */
define([ 'jquery'], 
         function($) {

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