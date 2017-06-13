'use strict';

app
  .controller('GenerateCtrl', ['$scope', '$http', '$alert','networksService', function ($scope, $http, $alert, networksService) {
		$scope.tooltipNeuronsPerLayer = {"title": "Insert numbers separated by spacebar e.g. 4 3 2 2. Every number defines the amount of neurons on the corresponding layer."};
		$scope.tooltipActivationFunction = {"title": "Insert the activation function and domain rules in format function<=>domain_rule1,domain_rule2. Domain rules are optional."};

		$scope.generate = function()
		{
			if(!validate())
				return;
			
			var neuPerLay = $scope.network.neuronsPerLayer.split(" ").map(function(item) {
				return parseInt(item, 10);
			});
			
			networksService.generate($scope.network.name, neuPerLay, $scope.network.activationFunction).success(function(data){
				$alert({title: 'Network generated', content: 'Network generation successfull', placement: 'top', type: 'info', show: true});
			}).error(function(err) {
				$alert({title: 'Unable to generate', content: data, placement: 'top', type: 'danger', show: true});
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
				error_message = "Name must not be empty!"
			$scope.network.neuronsPerLayer.split(" ").map(function(item) {
				if(!isPositiveInt(item))
					error_message = "Incorrect amount of neurons"
			});

			if(error_message != "")
			{
				$alert({title: 'Cannot register: ', content: error_message, placement: 'top', type: 'danger', show: true});
				return false;
			}
			return true;
		}
  }]);
