'use strict';

angular.module('nncloud')
  .controller('SaveAsCtrl', ['$scope','localizedMessageService', function($scope,localizedMessageService) 
{
	setLabels();

	function setLabels()
	{
		$scope.headerLabel = localizedMessageService.getLocalizedMessage('saveas.header');
		$scope.nameLabel = localizedMessageService.getLocalizedMessage('saveas.name');
		$scope.saveButtonLabel = localizedMessageService.getLocalizedMessage('saveas.saveButton');
	}
	
	$scope.saveAs = function()
	{
		
	}
}]);
