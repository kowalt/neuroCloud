'use strict';

angular.module('nncloud')
  .controller('NavbarCtrl',['$scope','$location', function ($scope,$location) {
	$scope.networks_dropdown = [
                {
                        "text": "Load...",
                        "href": "/core/load"
                },
                {
                        "divider": true
                },
                {
                        "text": "Save",
                        "href": "#"
                },
                {
                        "text": "Save as...",
			"href": "/core/saveas"
                },
                {
                        "divider": true
                },
                {
                        "text": "Delete",
                        "href": "#"
                }
	]	
	
	$scope.isActive = function(viewLocation)
	{
		return viewLocation === $location.path();	
	}
}]);
