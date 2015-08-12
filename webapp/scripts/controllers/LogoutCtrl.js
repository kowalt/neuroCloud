'use strict';

angular.module('nncloud')
  .controller('LogoutCtrl',['$scope', '$http', '$alert','$location', function($scope, $http, $alert, $location) {
	$scope.logout = function()
	{
		var request =
		{
			method: "GET",
			url: API['AUTH_LOGOUT']
		};
		
		$http(request).success(function(data, status, headers, config)
		{
			$rootScope.authorized = false;
			$location.path('/login');
			$alert({title: 'Logged out', content: "", placement: 'top', type: 'info', show: true});
		});
	}
  }]);
