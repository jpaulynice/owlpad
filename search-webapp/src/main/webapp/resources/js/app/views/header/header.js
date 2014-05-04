define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'bootstrap',
         'hbs!app/templates/header/header' ],
		function($, _, Backbone, Marionette,Bootstrap, headerTemplate) {
	
	var HeaderView = Backbone.Marionette.ItemView.extend({
		template:headerTemplate,
		className:"container-fluid",
		ui:{
			searchText:".searchText"
		},
		
		events:{
			"keypress .searchText": "handleEnter",
			"click .navbar-form .btn-default": "handleSearch"
		},
		
		initialize: function(options){
		
		},
		
		handleEnter: function(e){
			if ( e.which === 13 ) { 
		        this.handleSearch();
			}
		},
		
		handleSearch:function(){
			var keyWord = this.ui.searchText.val();
			if(keyWord){
				var data = {keyWord:keyWord,maxHits:20};
				this.trigger('searchapp:search',data);
			}
		}
		
	
	});


	return HeaderView;
});