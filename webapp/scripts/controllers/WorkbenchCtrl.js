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
		if(xmlNetwork === "")
			return;

		var nodes = transformNodes(xmlNetwork);

		for(var i=0;i<nodes.length; i++)
			nodes[i].size = 1;
		
		var edges = transformEdges(xmlNetwork);
		
		$scope.sigmaGraph = {
			nodes: transformNodes(xmlNetwork),		
			edges: transformEdges(xmlNetwork)
		}
	})
	.error(function(err)
	{
		$alert({title: 'Unable to load network', content: err, placement: 'top', type: 'danger', show: true});
	});
}]);
