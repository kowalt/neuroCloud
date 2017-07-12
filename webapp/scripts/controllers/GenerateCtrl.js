'use strict';

app
  .controller('GenerateCtrl', ['$scope', '$http', '$alert','networksService','localizedMessageService', function ($scope, $http, $alert, networksService, localizedMessageService) {
		$scope.tooltipNeuronsPerLayer = {"title": localizedMessageService.getLocalizedMessage('generate.tooltip.tooltipNPL.title')};
		$scope.tooltipActivationFunction = {"title": localizedMessageService.getLocalizedMessage('generate.tooltip.tooltipAF.title')};

		setLabels();
		
		function setLabels()
		{
			$scope.headerLabel = localizedMessageService.getLocalizedMessage('generate.header');
			$scope.nameLabel = localizedMessageService.getLocalizedMessage('generate.name');
			$scope.nplLabel = localizedMessageService.getLocalizedMessage('generate.npl');
			$scope.afLabel = localizedMessageService.getLocalizedMessage('generate.af');
			$scope.generateButtonLabel = localizedMessageService.getLocalizedMessage('generate.generateButton');			
		}
		
		$scope.generate = function()
		{
			if(!validate())
				return;
			
			var neuPerLay = $scope.network.neuronsPerLayer.split(" ").map(function(item) {
				return parseInt(item, 10);
			});
			
			networksService.generate($scope.network.name, neuPerLay, $scope.network.activationFunction).success(function(data){
				$alert({title: localizedMessageService.getLocalizedMessage('generate.alert.generatedOK.title'), content: localizedMessageService.getLocalizedMessage('generate.alert.generatedOK.content'), placement: 'top', type: 'info', show: true});
			}).error(function(err) {
				$alert({title: localizedMessageService.getLocalizedMessage('generate.alert.generatedNOK.title'), content: data, placement: 'top', type: 'danger', show: true});
			});
		}

		var validate = function()
		{
			function isPositiveInt(value) {
			  return !isNaN(value) && 
					 parseInt(Number(value)) == value && 
					 !isNaN(parseInt(value, 10)) && value > 0;
			}

			var error_message = "";

			if($scope.name == "")
				error_message = localizedMessageService.getLocalizedMessage('generate.alert.validationErrorMessage.nameMustNotBeEmpty')
			$scope.network.neuronsPerLayer.split(" ").map(function(item) {
				if(!isPositiveInt(item))
					error_message = localizedMessageService.getLocalizedMessage('generate.alert.validationErrorMessage.incorrectAmountOfNeurons');
			});

			if(error_message != "")
			{
				$alert({title: localizedMessageService.getLocalizedMessage('generate.alert.validationErrorMessage.title'), content: error_message, placement: 'top', type: 'danger', show: true});
				return false;
			}
			return true;
		}
  }]);
