'use strict';

app
  .controller('WorkbenchCtrl', ['$scope', '$cookies', '$alert', '$location', 'networksService','commonDataService','localizedMessageService', function ($scope, $cookies, $alert, $location,networksService,commonDataService,localizedMessageService) {
	
	setLabels();
	function setLabels()
	{
		$scope.inputLabel = localizedMessageService.getLocalizedMessage('workbench.input');
		$scope.outputLabel = localizedMessageService.getLocalizedMessage('workbench.output');
		$scope.uploadLabel = localizedMessageService.getLocalizedMessage('workbench.upload');
		$scope.downloadLabel = localizedMessageService.getLocalizedMessage('workbench.download');
		$scope.runButtonLabel = localizedMessageService.getLocalizedMessage('workbench.runButton');
	}

	$scope.run = function()
	{
		networksService.runNetwork($cookies.get('activeNetworkID'), $scope.inoutContainer.input_vector).success(function(data) {
			reload();
			$scope.inoutContainer.output_vector = data;
		}).error(function(err){
			$alert({title: localizedMessageService.getLocalizedMessage('workbench.alert.runNOK.title'), content: err, placement: 'top', type: 'danger', show: true });
		});
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
	reload();

	function reload()
	{
		networksService.getParticularNetwork(activeNetworkID).success(function(data) 
		{
			$scope.xmlNetwork = data;
			commonDataService.xmlNetwork = data;

			var nodes = transformNodes($scope.xmlNetwork);
			var transformEdgesResult = transformEdges($scope.xmlNetwork);
			var edges = transformEdgesResult[0];

			$scope.inoutContainer.input_vector = transformEdgesResult[1];
			$scope.inoutContainer.output_vector = transformEdgesResult[2];

			if(isNetworkTooBigToDraw(data))
			{	
				$alert({title: localizedMessageService.getLocalizedMessage('workbench.alert.tooBig'), content: '', placement: 'top', type: 'warning', show: true});
			}
			else
			{
				for(var i=0;i<nodes.length; i++)
					nodes[i].size = 1;

				$scope.sigmaGraph = {
					nodes: nodes,		
					edges: edges
				}
			}
		})
		.error(function(err)
		{
			$alert({title: localizedMessageService.getLocalizedMessage('workbench.alert.reloadNOK'), content: err, placement: 'top', type: 'danger', show: true});
		});	
	}
	
	function isNetworkTooBigToDraw(networkData)
	{
		var MAX_NUMBER_OF_LINES = 3000;
		var numberOfLines = networkData.split(/\r\n|\r|\n/).length;
		if(numberOfLines > MAX_NUMBER_OF_LINES)
			return true;
		return false;
	}
	
	$scope.downloadOutput = function()
	{
			var fileContent = "";
			var ov = $scope.inoutContainer.output_vector;
			for(var i=0;i<ov.length;i++)
			{
				if(i != ov.length-1)
					fileContent+=ov+",";
				else
					fileContent+=ov;
			}

			var element = document.createElement('a');
			element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(fileContent));
			element.setAttribute('download', 'output.csv');
			element.style.display = 'none';
			document.body.appendChild(element);
			element.click();
			document.body.removeChild(element);
	}
}]);
