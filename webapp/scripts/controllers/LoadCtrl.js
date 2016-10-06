'use strict';

angular.module('nncloud')
  .controller('LoadCtrl', ['$scope', '$cookies', '$location', 'networksService', function ($scope, $cookies, $location, networksService) {
	networkService.getNetworksList().success(function(data)
	{
		$scope.networks = data;
	}).error(function(err)
	{
		$alert({title: 'Unable to load list of networks', content: err, placement: 'top', type: 'danger', show: true});
	});
	
	$scope.selectNetwork = function(id)
	{
		$cookies.put('activeNetworkID', id);
		$location.path('/core/workbench');
	}
  }]);