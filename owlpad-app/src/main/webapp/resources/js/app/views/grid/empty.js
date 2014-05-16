/**
 * Represents a requirejs module for a dynamic Grid or Table
 */

define( [ 'jquery', 
          'underscore', 
          'backbone', 
          'marionette',
          'hbs!app/templates/grid/empty' ], 
          function($, _, Backbone, Marionette, emptyTemplate) {

	var EmptyView = Backbone.Marionette.ItemView.extend( {
		template : emptyTemplate,
		className : "empty-view"
	});

	return EmptyView;
});