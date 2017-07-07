'use strict';

app
  .controller('NavbarCtrl',['$scope','$location', '$cookies', '$alert','networksService','commonDataService','localizedMessageService', function ($scope, $location, $cookies, $alert, networksService, commonDataService, localizedMessageService) {
	
	setLabels();
	
	$scope.$on('change.the.language', function(event, value) {
		setLabels();
	});
	
	$scope.networks_dropdown = [
                {
                        "text": "Load...",
                        "href": "#/core/load"
                },
                {
                        "divider": true
                },
                {
                        "text": "Save",
                        "href": "#" //will be ng-click
                },
                {
                        "text": "Save as...",
						"href": "#/core/saveas"
                },
                {
                        "divider": true
                },
                {
                        "text": "Delete",
                        "click": "deleteNetwork()"
                }
	];
		
	function setLabels()
	{
		$scope.networksLabel = localizedMessageService.getLocalizedMessage('navbar.networks');
		$scope.workspaceLabel = localizedMessageService.getLocalizedMessage('navbar.workspace');
		$scope.generateLabel = localizedMessageService.getLocalizedMessage('navbar.generate');
		$scope.exportLabel = localizedMessageService.getLocalizedMessage('navbar.export');
		$scope.trainingLabel = localizedMessageService.getLocalizedMessage('navbar.training');
		$scope.settingsLabel = localizedMessageService.getLocalizedMessage('navbar.settings');
		$scope.exportLabel = localizedMessageService.getLocalizedMessage('navbar.export');
		$scope.logoutLabel = localizedMessageService.getLocalizedMessage('navbar.logout');
	}

	$scope.deleteNetwork = function()
	{
		networksService.deleteNetwork($cookies.get('activeNetworkID')).success(function(data) {
			$alert({title: 'Network deleted', content: 'Network deleted successfully', placement: 'top', type: 'info', show: true});
			$cookies.remove('activeNetworkID');
			$scope.xmlNetwork = commonDataService;
			$scope.xmlNetwork = '';
			$location.path('/core/load');
		}).
		error(function(err) {
			$alert({title: 'Unable to delete network', content: err, placement: 'top', type: 'danger', show: true});
		});
	}
	
	$scope.isActive = function(viewLocation)
	{
		return viewLocation === $location.path();	
	}
}]);
