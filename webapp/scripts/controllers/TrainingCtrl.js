'use strict';

app
  .controller('TrainingCtrl', ['$scope', '$alert', 'networksService','commonDataService','$interval','localizedMessageService', function ($scope, $alert,networksService,commonDataService,$interval,localizedMessageService) {
	
	setLabels();

	function setLabels()
	{
		$scope.headerLabel = localizedMessageService.getLocalizedMessage('training.header');
		$scope.lcLabel = localizedMessageService.getLocalizedMessage('training.lc');
		$scope.inputSetFileLabel = localizedMessageService.getLocalizedMessage('training.inputSetFile');
		$scope.outputSetFileLabel = localizedMessageService.getLocalizedMessage('training.outputSetFile');
		$scope.iterationsLabel = localizedMessageService.getLocalizedMessage('training.iterations');
		$scope.trainingButtonLabel = localizedMessageService.getLocalizedMessage('training.trainingButton');	
	}
	
	$scope.trainingProps = {};
	$scope.tooltipTraining = {"title":localizedMessageService.getLocalizedMessage('training.tooltip.title')};
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
			$scope.progressMessage = $scope.percentage.toString().substring(0,5)+"%("+iterationsDone+"/"+iterationsMax+")";
		}).error(function(err){
			$alert({title: localizedMessageService.getLocalizedMessage('training.alert.unableCheckCurrentlyTrained.title'), content: err, placement: 'top', type: 'danger', show: true });
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
			$alert({title: localizedMessageService.getLocalizedMessage('training.alert.sentOK.title'), content: data, placement: 'top', type: 'info', show: true });
		}).error(function(err){
			$alert({title: localizedMessageService.getLocalizedMessage('training.alert.sentNOK.title'), content: err, placement: 'top', type: 'danger', show: true });
		});
	}

	$scope.$on("$destroy", function(){
        $interval.cancel($scope.progressCheckInterval);
    });
}]);
