'use strict';

angular.module('nncloud')
  .controller('SaveAsCtrl', ['$scope','localizedMessageService', function($scope,localizedMessageService) 
{
	setLabels();

	function setLabels()
	{
		$scope.headerLabel = localizedMessageService.getLocalizedMessage('saveas.header');
		$scope.headerLabel = localizedMessageService.getLocalizedMessage('saveas.name');
		$scope.headerLabel = localizedMessageService.getLocalizedMessage('saveas.saveButton');
	}
}]);
