'use strict';
myApp.config(function ($routeProvider, $httpProvider){
        $routeProvider.
        when('/ListarUsuarios', {
          templateUrl: 'listausuarios.html',
          controller: 'UsuarioController',
            controllerAs:'UsuarioCtrl'

        }).
        when('/Login', {
          templateUrl: 'login.html',
          controller: 'LoginController',
            controllerAs: 'LoginCtrl'

        }).
        otherwise({
            redirectTo: '/Login'
        });

      $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    })
