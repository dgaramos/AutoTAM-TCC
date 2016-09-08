'use strict';
myApp.config(function ($routeProvider, $httpProvider){
        $routeProvider.
        when('/Inicial', {
          templateUrl: '/html/pages/inicial.html'

        }).
        when('/Login', {
            templateUrl: '/html/pages/login.html',
            controller: 'LoginController',
            controllerAs: 'LoginCtrl'

        }).
        when('/Cadastro', {
            templateUrl: 'html/pages/cadastrousuario.html'
        }).
        otherwise({
            redirectTo: '/Login'
        });

      $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    })
