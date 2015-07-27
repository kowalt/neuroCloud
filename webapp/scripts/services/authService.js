angular.module('authService', [])
.factory('authService', ['$http', function($http) {
	var login = function(login,password)
	{
		var credentials = {"login": login, "password": password};
		$http.post(API['LOGIN'],credentials).
		success(function(data, status, headers, config)
		{
			
		})
		.error(function(data, status, headers, config)
		{
			
		});
	};
	
	
	var register = function(user)
	{
		$http.post(API['REGISTER'],user).
		success(function(data, status, headers, config)
		{

		})
		.error(function(data, status, headers, config)
		{

		});
	};

	var logout = function()
	{
		
	};
});
