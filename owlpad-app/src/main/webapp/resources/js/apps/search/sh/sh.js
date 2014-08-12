define(['syntaxHighlighter',
         'shBrushJS'], 
         function(SyntaxHighlighter, JsBrush) {
    var SH = function() {

        /**
         * Call the search endpoint
         */
        this.setup = function() {
            SyntaxHighlighter.highlight({
                toolbar: false,
                gutter: true
            });
        };
    };
    
    return SH;
});