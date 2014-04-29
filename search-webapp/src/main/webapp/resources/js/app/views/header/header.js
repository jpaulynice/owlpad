define([ 'jquery', 'underscore', 'backbone', 'marionette',
				'app/views/grid/gridRow', 'hbs!app/templates/header/header' ],
		function($, _, Backbone, Marionette, GridRow, headerTemplate) {
	
	var HeaderView = Backbone.Marionette.ItemView.extend({
		template:headerTemplate,
		className:"container-fluid",
		ui:{
			searchText:".searchText"
		},
		
		events:{
			"click .navbar-form .btn-default": "handleSearch"
		},
		
		initialize: function(options){
		
		},
		
		handleSearch:function(e){
			var keyWord = this.ui.searchText.val();
			var data = {keyWord:keyWord,maxHits:20};
			this.trigger('searchapp:search',data);
		}
		
	
	});


	return HeaderView;
});