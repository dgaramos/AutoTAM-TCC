'use strict';

var services = angular.module('autoTAMServices', []);

var controllers = angular.module('autoTAMControllers', ['autoTAMServices']);

var myApp = angular.module('clientAutoTAM', ['ngRoute', 'ngMessages', 'autoTAMControllers', 'autoTAMServices']);


