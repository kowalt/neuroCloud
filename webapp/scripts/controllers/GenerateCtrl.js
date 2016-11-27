'use strict';

app
  .controller('GenerateCtrl', ['$scope', '$http', '$alert', function ($scope, $http, $alert) {
		$scope.generate = function()
		{
			if(!validate())
				return;
			
			var neuPerLay = $scope.network.neuronsPerLayer.split(" ").map(function(item) {
				return parseInt(item, 10);
			});
			
			var request = 
			{
				method: "POST",
				url: API['GENERATE'],
				headers:
				{
					"Content-Type": "application/json",
				},
				data: {"name": $scope.network.name ,"neuronsPerLayer": neuPerLay, "activationFunction": $scope.network.activationFunction }
			};

			$http(request).success(function(data, status, headers, config)
			{
				$alert({title: 'Network generated', content: 'Network generation successfull', placement: 'top', type: 'info', show: true});				
			})
			.error(function(data, status, headers, config)
			{
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
