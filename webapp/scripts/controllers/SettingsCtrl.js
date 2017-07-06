'use strict';

app.controller('SettingsCtrl', ['$scope','localizedMessageService','$cookies', function ($scope,localizedMessageService,$cookies) {
	$scope.dropdown = localizedMessageService.getDropdown();
	$scope.headerLabel = localizedMessageService.getLocalizedMessage('settings.header');
	$scope.selectLanguageLabel = localizedMessageService.getLocalizedMessage('settings.selectLanguage');
	
	$scope.setLangCode = function(code)
	{
		$cookies.put("language", code);
	}
  }]);
