'use strict';

app.controller('SettingsCtrl', ['$scope','localizedMessageService','$cookies','$rootScope', function ($scope,localizedMessageService,$cookies,$rootScope) {
	$scope.dropdown = localizedMessageService.getDropdown();

	setLabels();

	function setLabels()
	{
		$scope.headerLabel = localizedMessageService.getLocalizedMessage('settings.header');
		$scope.selectLanguageLabel = localizedMessageService.getLocalizedMessage('settings.selectLanguage');
	}

	$scope.setLangCode = function(code)
	{
		$rootScope.$broadcast('change.the.language','foo');
		$cookies.put('language', code);
		setLabels();
	}
  }]);
