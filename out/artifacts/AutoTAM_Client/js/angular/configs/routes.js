'use strict';
myApp.config(function ($routeProvider, $httpProvider){
        $routeProvider.
        when('/ListarUsuarios', {
          templateUrl: 'listausuarios.html',
          controller: 'UsuarioController'
          
        }).
        when('/Login', {
          templateUrl: 'login.html',
          controller: 'LoginController'

        }).
        otherwise({
            redirectTo: '/Login'
        });

      $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    })

