'use strict';

app.controller('LoginCtrl', ['$scope', '$http', '$alert', '$rootScope', '$location', function ($scope, $http, $alert, $rootScope, $location) {
	if($rootScope.authorized)
		$location.path('/core/workbench');

	$scope.login = function()
	{
			var request = 
			{	
				withCredentials: true,
				method: "POST",
				url: API['AUTH_LOGIN'],
				headers:
				{
					"Content-Type": "application/json"
				},
				data: { "login": $scope.user.login, "password": $scope.user.password }
			};

			$http(request).success(function(data, status, headers, config)
			{	
				$rootScope.authorized = true;

				$location.path('/core/workbench');
				})
			.error(function(data, status, headers, config)
			{
				var msg;
				if(data != null)
					msg = data.error;
				else
					msg = "Untypical error. Code: " + status.toString();
				$alert({title: 'Unable to sign in:', content: msg, placement: 'top', type: 'danger', show: true});
			});
			
			$scope.user.password = '';
	};
}]);
