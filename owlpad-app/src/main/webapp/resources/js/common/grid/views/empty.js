/**
 * Represents a requirejs module for an empty view
 */

define( [ 'jquery', 
          'underscore', 
          'backbone', 
          'marionette',
          'hbs!common/grid/templates/empty' ], 
          function($, _, Backbone, Marionette, emptyTemplate) {

	var EmptyView = Backbone.Marionette.ItemView.extend({
		template : emptyTemplate,
		className : "empty-view"
	});

	return EmptyView;
});