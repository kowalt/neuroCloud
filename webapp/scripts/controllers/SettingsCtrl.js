'use strict';

app.controller('SettingsCtrl', ['$scope','localizedMessageService', function ($scope,localizedMessageService) {
	$scope.dropdown = localizedMessageService.getDropdown();
	$scope.headerValue = localizedMessageService.getLocalizedMessage('settings.header');
  }]);
