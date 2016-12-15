'use strict';

app
  .controller('TrainingCtrl', ['$scope', '$alert', 'networksService','commonDataService', function ($scope, $alert,networksService,commonDataService) {
	$scope.trainingProps = {};

	function obtainNetworkId(networkXML)
	{
		parser = new DOMParser();
		xmlDoc = parser.parseFromString(networkXML, "application/xml");
		var network = xmlDoc.getElementsByTagName("network")[0];
		return network.getAttribute('id');
	}

	$scope.train = function() {
		$scope.xmlNetwork = commonDataService.xmlNetwork;
		$scope.trainingProps.networkId = obtainNetworkId($scope.xmlNetwork);
		networksService.train($scope.trainingProps).success(function(data) {
			$alert({title: 'Network sent for training', content: data, placement: 'top', type: 'info', show: true });
		}).error(function(err){
			$alert({title: 'Unable to train network', content: err, placement: 'top', type: 'danger', show: true });
		});
	}
}]);
