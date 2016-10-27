'use strict';

app
  .controller('NavbarCtrl',['$scope','$location', '$cookies', '$alert','networksService','commonDataService', function ($scope, $location, $cookies, $alert, networksService, commonDataService) {
	$scope.networks_dropdown = [
                {
                        "text": "Load...",
                        "href": "#/core/load"
                },
                {
                        "divider": true
                },
                {
                        "text": "Save",
                        "href": "#" //will be ng-click
                },
                {
                        "text": "Save as...",
						"href": "#/core/saveas"
                },
                {
                        "divider": true
                },
                {
                        "text": "Delete",
                        "click": "deleteNetwork()"
                }
	]
	
	$scope.deleteNetwork = function()
	{
		networksService.deleteNetwork($scope.activeNetworkID).success(function(data) {
			$alert({title: 'Network deleted', content: 'Network deleted successfully', placement: 'top', type: 'info', show: true});
			$cookies.remove('activeNetworkID');
			$scope.xmlNetwork = commonDataService;
			$scope.xmlNetwork = '';
			$location.path('/core/load');
		}).
		error(function(err) {
			$alert({title: 'Unable to delete network', content: err, placement: 'top', type: 'danger', show: true});
		});
	}
	
	$scope.isActive = function(viewLocation)
	{
		return viewLocation === $location.path();	
	}
}]);
