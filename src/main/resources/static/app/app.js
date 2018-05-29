'use strict';

var services = angular.module('autoTAMServices', []);

var controllers = angular.module('autoTAMControllers', ['autoTAMServices']);

var env = {};

// Import variables if present (from env.js)
if(window){
    Object.assign(env, window.__env);
}

var myApp = angular.module('clientAutoTAM', ['ngRoute', 'ngMessages', 'chart.js', 'autoTAMControllers', 'autoTAMServices']);

myApp.constant('__env', env);



