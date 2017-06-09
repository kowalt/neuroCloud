'use strict';

angular.module('nncloud')
  .controller('ExportCtrl', ['$scope','commonDataService', function ($scope, commonDataService) {
	  $scope.formats = [{name:"XML"}];
	  $scope.download = function()
	  {
		  if($scope.selectedFormat == "XML")
		  {
			var blob = new Blob(commonDataService.xmlNetwork,  {type:"application/xml;charset=utf-8;" });
			var downloadLink = angular.element('<a></a>');
			downloadLink.attr('href',window.URL.createObjectURL(blob));
			downloadLink.attr('download', 'network.xml');
			downloadLink[0].click();
		  }
	  }
  });
