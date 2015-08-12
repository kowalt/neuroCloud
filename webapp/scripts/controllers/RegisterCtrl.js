'use strict';

angular.module('nncloud')
  .controller('RegisterCtrl', ['$scope', '$http', '$location', '$alert', function ($scope,$http,$location,$alert) {
	$scope.register = function()
	{
		if(!validate())
			return;

		var request = 
		{
			method: "POST",
			url: API['AUTH_REGISTER'],
			headers:
			{
				"Content-Type": "application/json"
			},
			data: { "email": $scope.user.email, "login": $scope.user.login, "password": $scope.user.password, "info_to_admin": $scope.user.message_to_admin }
		}
		$http(request).success(function(data, status, headers, config)
		{
			$alert({title: 'Data have been submitted successfully.', content: 'We will look at your request.', placement: 'top', type: 'success', show: true});
			$location.path('/login');
		})
		.error(function(data, status, headers, config)
		{
			$alert({title: 'Cannot register: ', content: data.error, placement: 'top', type: 'danger', show: true});
		});
	}

	var validate = function()
	{
		var error_message = "";
		
		if($scope.user.password != $scope.password_confirmation)
			error_message = "passwords doesn't match!";
		
		if(error_message != "")
		{
			$alert({title: 'Cannot register: ', content: error_message, placement: 'top', type: 'danger', show: true});
			return false;
		}

		return true;
	}
  }]);
