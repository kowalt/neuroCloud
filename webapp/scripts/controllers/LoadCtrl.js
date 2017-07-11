'use strict';

app
  .controller('LoadCtrl', ['$scope', '$cookies', '$location', '$alert', 'networksService', 'commonDataService','localizedMessageService', function ($scope, $cookies, $location, $alert, networksService, commonDataService, localizedMessageService) {
	
	function setLabels()
	{
		$scope.loadViewHeaderLabel = localizedMessageService.getLocalizedMessage('load.loadViewHeader');
		$scope.createdInLabel = localizedMessageService.getLocalizedMessage('load.createdIn');
	}
	
	setLabels();
	
	networksService.getNetworksList().success(function(data) {
		$scope.networks = data;
	})
	.error(function(err) {
		$alert({title: localizedMessageService.getLocalizedMessage('load.alert.loadListNOK.title'), content: err, placement: 'top', type: 'danger', show: true});
	});

	$scope.selectNetwork = function(id)
	{
		$cookies.put('activeNetworkID', id);
		$location.path('/core/workbench');
	}
  }]);