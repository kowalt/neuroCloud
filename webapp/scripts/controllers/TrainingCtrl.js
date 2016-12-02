'use strict';

app
  .controller('TrainingCtrl', ['$scope', '$alert', 'networksService','commonDataService', function ($scope, $alert,networksService,commonDataService) {
	$scope.trainingProps = {};

	function digestCSV(rawContent)
	{
		var retArr = [];
		var arrayOfLines = rawContent.match(/[^\r\n]+/g);
		
		for(var i=0; i<arrayOfLines; i++)
			retArr[i] = arrayOfLines[i].split(',');
			
		return retArr;
	}

	function obtainNetworkId(networkXML)
	{
		parser = new DOMParser();
		xmlDoc = parser.parseFromString(networkXML, "application/xml");
		var network = xmlDoc.getElementsByTagName("network");
		return network.getAttribute('id');
	}

	$scope.addLearningData = function() {
		var f = document.getElementById('learningSet').files[0], r = new FileReader();
		r.onloadend = function(e) {
			$scope.trainingProps.learningSet = digestCSV(e.target.result);
		}
		r.readAsBinaryString(f);
	}

	$scope.addTrainingData = function() {
		var f = document.getElementById('trainingSet').files[0], r = new FileReader();
		r.onloadend = function(e) {
			$scope.trainingProps.trainingSet = digestCSV(e.target.result);
		}
		r.readAsBinaryString(f);
	}
	
	$scope.train = function() {
		$scope.xmlNetwork = commonDataService;
		$scope.trainingProps.networkId = obtainNetworkId($scope.xmlNetwork);
		networksService.train($scope.trainingProps).success(function(data) {
			$alert({title: 'Network sent for training', content: data, placement: 'top', type: 'info', show: true });
		}).error(function(err){
			$alert({title: 'Unable to train network', content: err, placement: 'top', type: 'danger', show: true });
		});
	}
}]);
