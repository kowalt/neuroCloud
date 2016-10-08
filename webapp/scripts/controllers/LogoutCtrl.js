'use strict';

app
  .controller('LogoutCtrl',['$scope', '$http', '$alert','$location', '$rootScope', function($scope, $http, $alert, $location, $rootScope) {
	$scope.logout = function()
	{
		var request =
		{	
			withCredentials: true,
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
