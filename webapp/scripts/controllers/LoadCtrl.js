'use strict';

app
  .controller('LoadCtrl', ['$scope', '$cookies', '$location', '$alert', 'networksService', function ($scope, $cookies, $location, $alert, networksService) {
	try
	{
		$scope.networks = networksService.getNetworksList();
	}
	catch(err)
	{
		$alert({title: 'Unable to load list of networks', content: err, placement: 'top', type: 'danger', show: true});
	}

	$scope.selectNetwork = function(id)
	{
		$cookies.put('activeNetworkID', id);
		$location.path('/core/workbench');
	}
  }]);