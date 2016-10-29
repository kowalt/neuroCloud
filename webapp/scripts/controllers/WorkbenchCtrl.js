'use strict';

app
  .controller('WorkbenchCtrl', ['$scope', '$cookies', '$alert', '$location', 'networksService','commonDataService', function ($scope, $cookies, $alert, $location,networksService,commonDataService) {
	$scope.run = function()
	{
		
	}

	$scope.inout_tracking = function()
	{
		var lenInput = $scope.inoutContainer.input_vector.length;
		var lenOutput = $scope.inoutContainer.output_vector.length;

		if(lenInput > lenOutput)
			return $scope.inoutContainer.input_vector;
		else
			return $scope.inoutContainer.output_vector;	
	}

	$scope.inoutContainer = {input_vector: [], output_vector: []};
	//get active network
	var activeNetworkID = $cookies.get('activeNetworkID');
	if(activeNetworkID === undefined)
	{	
		$location.path('/core/load');
		return;
	}
	$scope.xmlNetwork = commonDataService;
	
	networksService.getParticularNetwork(activeNetworkID).success(function(data) 
	{
		$scope.xmlNetwork = data;

		var nodes = transformNodes($scope.xmlNetwork);
		var transformEdgesResult = transformEdges($scope.xmlNetwork);
		var edges = transformEdgesResult[0];

		$scope.inoutContainer.input_vector = transformEdgesResult[1];
		$scope.inoutContainer.output_vector = transformEdgesResult[2];

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
