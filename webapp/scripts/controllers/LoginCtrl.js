'use strict';

angular.module('nncloud').controller('LoginCtrl', ['$scope', '$http', '$injector', '$rootScope', '$location', function ($scope) {
    if($rootScope.authorized)
	$location.href('/workbench');

    var TOAST_DURATION = 5000;
  
    $scope.login = function()
    {
	credentials = { "login": user.login, "password": user.password };
	$http.post(API['AUTH_LOGIN'], credentials).success(function(data, status, headers, config)
	{	
		$rootScope.authorized = true;
		$location.href('/workbench');
    	})
	.error(function(data, status, headers, config)
	{
		Materialize.toast(data.error, TOAST_DURATION);
	});
    };
}]);
