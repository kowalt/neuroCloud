'use strict';

app.controller('SettingsCtrl', ['$scope','localizedMessageService','$cookies', function ($scope,localizedMessageService,$cookies) {
	$scope.dropdown = localizedMessageService.getDropdown();
	$scope.headerValue = localizedMessageService.getLocalizedMessage('settings.header');
	
	$scope.setLangCode = function(code)
	{
		$cookies.put("language", code);
	}
  }]);
