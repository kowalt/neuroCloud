'use strict';

app
  .controller('NavbarCtrl',['$scope','$location', '$cookies', '$alert','networksService','commonDataService','localizedMessageService', function ($scope, $location, $cookies, $alert, networksService, commonDataService, localizedMessageService) {
	
	setLabels();
	createDropDown();
	
	$scope.$on('change.the.language', function(event, value) {
		setLabels();
		createDropDown();
	});
	
	function createDropDown()
	{
		$scope.networks_dropdown = [
					{
							"text": localizedMessageService.getLocalizedMessage('navbar.dropdown.load'),
							"href": "#/core/load"
					},
					{
							"divider": true
					},
					{
							"text": localizedMessageService.getLocalizedMessage('navbar.dropdown.save'),
							"href": "#" //will be ng-click
					},
					{
							"text": localizedMessageService.getLocalizedMessage('navbar.dropdown.saveas'),
							"href": "#/core/saveas"
					},
					{
							"divider": true
					},
					{
							"text": localizedMessageService.getLocalizedMessage('navbar.dropdown.delete'),
							"click": "deleteNetwork()"
					}
		];
	}
	
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
			$alert({title: localizedMessageService.getLocalizedMessage('navbar.alert.deleteOK.title'), content: localizedMessageService.getLocalizedMessage('navbar.alert.deleteOK.content'), placement: 'top', type: 'info', show: true});
			$cookies.remove('activeNetworkID');
			$scope.xmlNetwork = commonDataService;
			$scope.xmlNetwork = '';
			$location.path('/core/load');
		}).
		error(function(err) {
			$alert({title: localizedMessageService.getLocalizedMessage('navbar.alert.deleteNOK.title'), content: err, placement: 'top', type: 'danger', show: true});
		});
	}
	
	$scope.isActive = function(viewLocation)
	{
		return viewLocation === $location.path();	
	}
}]);
