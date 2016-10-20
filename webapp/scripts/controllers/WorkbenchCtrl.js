'use strict';

app
  .controller('WorkbenchCtrl', ['$scope', '$cookies', '$alert', '$location', 'networksService', function ($scope, $cookies, $alert, $location,networksService) {
	//get active network
	var activeNetworkID = $cookies.get('activeNetworkID');
	if(activeNetworkID === undefined)
	{	
		$location.path('/core/load');
		return;
	}
	var xmlNetwork = "";
	
	networksService.getParticularNetwork(activeNetworkID).success(function(data) 
	{
		xmlNetwork = data

		var nodes = transformNodes(xmlNetwork);
		var edges = transformEdges(xmlNetwork);
		
		for(var i=0;i<nodes.length; i++)
			nodes[i].size = 1;
		
		$scope.sigmaGraph = {
			nodes: nodes,		
			edges: edges
		}
	})
	.error(function(err)
	{
		$alert({title: 'Unable to load network', content: err, placement: 'top', type: 'danger', show: true});
	});
}]);
