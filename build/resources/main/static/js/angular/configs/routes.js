'use strict';
myApp.config(function ($routeProvider, $httpProvider){
        $routeProvider.
        when('/Inicial', {
          templateUrl: 'inicial.html'

        }).
        when('/Login', {
            templateUrl: 'login.html',
            controller: 'LoginController',
            controllerAs: 'LoginCtrl'

        }).
        when('/Cadastro', {
            templateUrl: 'html/noauth/cadastrousuario.html'
        }).
        otherwise({
            redirectTo: '/Login'
        });

      $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    })
