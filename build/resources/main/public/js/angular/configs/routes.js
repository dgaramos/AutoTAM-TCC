'use strict';
myApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
        when('/Login', {
            templateUrl: 'login.html'
        }).
        when('/ListarUsuarios', {
            templateUrl: 'listausuarios.html',
            controller: 'UsuarioController'
        }).
        otherwise({
            redirectTo: '/Login'
        });
    }])

