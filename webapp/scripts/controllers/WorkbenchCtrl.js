'use strict';

angular.module('nncloud')
  .controller('WorkbenchCtrl', ['$scope', function ($scope) {
	//get active network
	var activeNetworkID = $cookies.get('activeNetworkID');
	if(activeNetworkID !== undefined)
		$location.path('/core/load')
	
	var xmlNetwork = 
	
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
