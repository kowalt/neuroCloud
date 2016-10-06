'use strict';

angular.module('nncloud')
  .controller('WorkbenchCtrl', ['$scope', '$cookies', 'networksService', function ($scope, $cookies, networksService) {
	//get active network
	var activeNetworkID = $cookies.get('activeNetworkID');
	if(activeNetworkID !== undefined)
		$location.path('/core/load');
	var xmlNetwork = "";
	
	networkService.getParticularNetwork(activeNetworkID).success(function(data) 
	{
		xmlNetwork = data;
	})
	.error(function(err)
	{
		$alert({title: 'Unable to load network', content: err, placement: 'top', type: 'danger', show: true});
	});

	if(xmlNetwork === "")
		return;
	
	var nodes = transformNodes(xmlNetwork);
	
	for(var i=0;i<nodes.length; i++)
	{
		nodes[i].size = 1;
	}
	var edges = transformEdges(xmlNetwork);
	
	$scope.sigmaGraph = {
		nodes: transformNodes(xmlNetwork),
	
		edges: transformEdges(xmlNetwork)
	}
}]);
