'use strict';

angular.module('nncloud')
  .controller('NavbarCtrl',['$scope', function ($scope) {
	$scope.networks_dropdown = [
                {
                        "text": "Load...",
                        "href": "#"
                },
                {
                        "divider": true
                },
                {
                        "text": "Save",
                        "href": "#"
                },
                {
                        "text": "Save as..."
                },
                {
                        "divider": true
                },
                {
                        "text": "Delete",
                        "href": "#"
                }
]}]);
