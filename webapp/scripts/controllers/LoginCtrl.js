'use strict';

angular.module('nncloud').controller('LoginCtrl', ['$scope', '$http', '$injector', '$rootScope', '$location', function ($scope, $http, $injector, $rootScope, $location) {
	if($rootScope.authorized)
	$location.path('/core/workbench');

	var TOAST_DURATION = 5000;

	$scope.login = function()
	{
			var request = 
			{	
				method: "POST",
				url: API['AUTH_LOGIN'],
				headers:
				{
					"Content-Type": "application/json"
				},
				data: { "login": $scope.user.login, "password": $scope.user.password }
			}

			$http(request).success(function(data, status, headers, config)
			{	
				$rootScope.authorized = true;

				$location.path('/core/workbench');
				})
			.error(function(data, status, headers, config)
			{
				if(data != null)
					Materialize.toast(data.error, TOAST_DURATION);
				else
					Materialize.toast(status.toString(), TOAST_DURATION);
			});
			
			$scope.user.password = '';
	};
}]);
