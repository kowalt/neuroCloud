'use strict';

app
  .controller('LoadCtrl', ['$scope', '$cookies', '$location', '$alert', 'networksService', 'commonDataService', function ($scope, $cookies, $location, $alert, networksService, commonDataService) {
	networksService.getNetworksList().success(function(data) {
		$scope.networks = data;
	})
	.error(function(err) {
		$alert({title: 'Unable to load list of networks', content: err, placement: 'top', type: 'danger', show: true});
	});

	$scope.selectNetwork = function(id)
	{
		networksService.getParticularNetwork(activeNetworkID).success(function(data) 
		{
			$scope.xmlNetwork = data;
		})
		.error(function(err)
		{
			$alert({title: 'Unable to load network', content: err, placement: 'top', type: 'danger', show: true});
		});

		$cookies.put('activeNetworkID', id);
		$location.path('/core/workbench');
	}
  }]);