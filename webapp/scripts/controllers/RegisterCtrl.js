'use strict';

angular.module('nncloud')
  .controller('RegisterCtrl',['$scope', '$http', '$location', function ($scope,$http,$location) {
	$scope.register = function()
	{
		var request = 
		{
			method: "POST",
			url: API['AUTH_REGISTER'],
			headers:
			{
				"Content-Type": "application/json"
			},
			data: { "email": $scope.user.email, "login": $scope.user.login, "password": $scope.user.password, "message_to_admin": $scope.user.message_to_admin }
		}
		$http(request).success(function(data, status, headers, config)
		{
			Materialize.toast("Data has been submitted successfully. We will look at your request.", 9000);
			$location.path('/login');
		})
		.error(function(data, status, headers, config)
		{
			Materialize.toast(data.error, 9000);
		});
		
	}
  }]);
