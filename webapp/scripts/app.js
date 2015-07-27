/**
 * @ngdoc overview
 * @name webappApp
 * @description
 * # webappApp
 *
 * Main module of the application.
 */
var app = angular.module('nncloud', ['ngRoute']);
  app.config(function ($routeProvider) {
    $routeProvider
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .when('/register', {
        templateUrl: 'views/register.html',
        controller: 'RegisterCtrl'
      })
      .when('/core/workplace', {
	templateUrl: 'views/workplace.html',
	controller: 'WorkplaceCtrl'
      })
      .when('/core/generate', {
	templateUrl: 'views/generate.html',
	controller: 'GenerateCtrl'
      })
      .when('/core/export', {
	templateUrl: 'views/core/export.html',
	controller: 'ExportCtrl'
      })
      .when('/core/settings', {
	templateUrl: 'views/core/settings.html',
	controller: 'SettingsCtrl'
      })
      .when('/core/load', {
	templateUrl: 'views/core/load.html',
	controller: 'LoadCtrl'
      })
      .when('/core/saveas', {
	templateUrl: 'views/core/saveas.html',
	controller: 'SaveAsCtrl'
      })
      .when('/core/logout', {
	templateUrl: 'views/login.html',
	controller: 'LogoutCtrl'
      })
      .otherwise({
        redirectTo: '/login'
      });
  });
