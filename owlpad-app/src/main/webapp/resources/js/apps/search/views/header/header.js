define([ 'jquery', 
         'underscore', 
         'backbone', 
         'marionette',
         'bootstrap',
         'hbs!apps/search/templates/header/header' ],
         function($, _, Backbone, Marionette,Bootstrap, headerTemplate) {
	
	var HeaderView = Backbone.Marionette.ItemView.extend({
		template:headerTemplate,
		className:"container-fluid",
		ui:{
			searchText:".search-input"
		},
		
		events:{
			"keypress .search-input": "handleEnter",
			"click .fa-search": "handleSearch"
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
				var data = {keyWord:keyWord,maxHits:10};
				this.trigger('searchapp:search',data);
			}
		}
	});


	return HeaderView;
});