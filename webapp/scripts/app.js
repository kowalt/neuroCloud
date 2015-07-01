'use strict';

/**
 * @ngdoc overview
 * @name webappApp
 * @description
 * # webappApp
 *
 * Main module of the application.
 */
angular
  .module('webappApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
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
	controller: 'WorkplaceCtrl'
      })
      .when('/core/saveas', {
	templateUrl: 'views/core/saveas.html',
	controller: 'WorkplaceCtrl'
      })
      .when('/core/logout', {
	templateUrl: 'views/login.html',
	controller: 'LogoutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
