'use strict';

app
  .controller('LogoutCtrl',['$scope', '$http', '$alert','$location', '$rootScope','localizedMessageService', function($scope, $http, $alert, $location, $rootScope, localizedMessageService) {
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
			$alert({title: localizedMessageService('logout.alert.loggedOut.title'), content: "", placement: 'top', type: 'info', show: true});
		});
	}
  }]);
