'use strict';

app
  .controller('TrainingCtrl', ['$scope', '$alert', 'networksService','commonDataService','$interval', function ($scope, $alert,networksService,commonDataService,$interval) {
	$scope.trainingProps = {};
	$scope.tooltipTraining = {"title":"File with floating-point values partitioned by comma. Vectors' separator is crlf"};
	$scope.isCurrentlyTrained = false;
	$scope.progressMessage = "";
	$scope.percentage;
	var PERIOD = 3000; //Milliseconds between every progres check
	
	updateProgress();
	
	function connectWithProgressSocket()
	{
		//Todo
	}

	function checkIfCurrentlyTrained()
	{
		var networkId = obtainNetworkId(commonDataService.xmlNetwork);

		var iterationsMax = -1;
		var iterationsDone = -1;
		networksService.getProgress(networkId).success(function(data) {
			iterationsMax = data.iterationsMax[0];
			iterationsDone = data.iterationsDone[0];
			if(iterationsMax != iterationsDone)
				$scope.isCurrentlyTrained = true;
			else
				$scope.isCurrentlyTrained = false;
			
			$scope.percentage = iterationsDone/iterationsMax * 100;
			
			$scope.progressMessage = $scope.percentage.toString()+"%("+iterationsDone+"/"+iterationsMax+")";
		}).error(function(err){
			$alert({title: 'Unable to check if network is currently trained', content: err, placement: 'top', type: 'danger', show: true });
		});
	}
	
	function updateProgress()
	{
		$scope.progressCheckInterval = $interval(checkIfCurrentlyTrained, PERIOD);
	}

	function obtainNetworkId(networkXML)
	{
		var parser = new DOMParser();
		xmlDoc = parser.parseFromString(networkXML, "application/xml");
		var network = xmlDoc.getElementsByTagName("network")[0];
		return network.getAttribute('id');
	}

	$scope.train = function() {
		$scope.xmlNetwork = commonDataService.xmlNetwork;
		$scope.trainingProps.networkId = parseInt(obtainNetworkId($scope.xmlNetwork));
		$scope.trainingProps.iterations = parseInt($scope.trainingProps.iterations);
		$scope.trainingProps.learningCoefficient = parseFloat($scope.trainingProps.learningCoefficient);
		networksService.train($scope.trainingProps).success(function(data) {
			$alert({title: 'Network sent for training', content: data, placement: 'top', type: 'info', show: true });
		}).error(function(err){
			$alert({title: 'Unable to train network', content: err, placement: 'top', type: 'danger', show: true });
		});
	}
	
	$scope.$on("$destroy", function(){
        $interval.cancel($scope.progressCheckInterval);
    });
}]);
