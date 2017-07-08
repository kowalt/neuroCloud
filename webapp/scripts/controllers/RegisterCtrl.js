'use strict';

app
  .controller('RegisterCtrl', ['$scope', '$http', '$location', '$alert','localizedMessageService', function ($scope,$http,$location,$alert,localizedMessageService) {
	  
	setLabels();

	function setLabels()
	{
		$scope.signUpLabel = localizedMessageService.getLocalizedMessage('register.signUp');
		$scope.emailLabel = localizedMessageService.getLocalizedMessage('register.email');
		$scope.loginLabel = localizedMessageService.getLocalizedMessage('register.login');
		$scope.passwordLabel = localizedMessageService.getLocalizedMessage('register.password');
		$scope.confirmPasswordLabel = localizedMessageService.getLocalizedMessage('register.confirmPassword');
		$scope.enterTheMessageForAdminHereLabel = localizedMessageService.getLocalizedMessage('register.enterTheMessageForAdminHere');
		$scope.registerLabel = localizedMessageService.getLocalizedMessage('register.register');
		$scope.backToTheLoginPromptLabel = localizedMessageService.getLocalizedMessage('register.backToTheLoginPrompt');
	} 
	  
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
			$alert({title: localizedMessageService.getLocalizedMessage('register.alert.dataSubmitOK.title'), content: localizedMessageService.getLocalizedMessage('register.alert.dataSubmitOK.content'), placement: 'top', type: 'success', show: true});
			$location.path('/login');
		})
		.error(function(data, status, headers, config)
		{
			$alert({title: localizedMessageService.getLocalizedMessage('register.alert.dataSubmitNOK.title'), content: data.error, placement: 'top', type: 'danger', show: true});
		});
	}

	var validate = function()
	{
		var error_message = "";
		
		if($scope.user.password != $scope.password_confirmation)
			error_message = localizedMessageService.getLocalizedMessage('register.alert.errorMessagePasswordsDM');
		
		if(error_message != "")
		{
			$alert({title: localizedMessageService.getLocalizedMessage('register.alert.dataSubmitNOK.title'), content: error_message, placement: 'top', type: 'danger', show: true});
			return false;
		}

		return true;
	}
  }]);
