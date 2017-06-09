'use strict';

angular.module('nncloud')
  .controller('ExportCtrl', ['$scope','commonDataService', function ($scope, commonDataService) {
	  $scope.formats = [{name:"XML"}];
	  $scope.download = function()
	  {
		  if($scope.selectedFormat.name == "XML")
		  {
			var fileContent = commonDataService.xmlNetwork;
			var element = document.createElement('a');
			element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(fileContent));
			element.setAttribute('download', 'myNetwork.xml');
			element.style.display = 'none';
			document.body.appendChild(element);
			element.click();
			document.body.removeChild(element);
		  }
	  }
  }]);
