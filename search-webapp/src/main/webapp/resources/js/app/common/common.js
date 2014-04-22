EventManager = _.extend({}, Backbone.Events);

EventManager.subscribe = function(channel, eventName, handler){
	if(_.isFunction(handler)){
		EventManager.bind(channel, eventName,handler)		
	}
}

EventManager.publish = function(eventName){
	if(console){
		console.log("Triggering... "+eventName);
		EventManager.trigger(eventName);
	}
}