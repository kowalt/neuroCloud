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
}]);
