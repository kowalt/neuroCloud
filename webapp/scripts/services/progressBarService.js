angular.module('progressBarService', ['ngWebSocket'])
.factory('progressBarService', ['$websocket', function($websocket) {
	var DataStream = $websocket('ws://178.62.119.189:8080/nncloudAPI/trainingprogress')
	
	var collection = [];
	
	dataStream.onMessage(function(message)
	{
		
	});
});