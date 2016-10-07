'use strict';

app
  .controller('NavbarCtrl',['$scope','$location', function ($scope, $location) {
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
                        "href": "#" //will be ng-click
                }
	]
	
	$scope.isActive = function(viewLocation)
	{
		return viewLocation === $location.path();	
	}
}]);
