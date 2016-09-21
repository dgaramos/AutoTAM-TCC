'use strict';

myApp.config(function ($routeProvider, $httpProvider){
        $routeProvider.
        when('/Inicial', {
          templateUrl: '/app/pages/inicial.html'

        }).

        when('/RecuperaSenha', {
            templateUrl: '/app/pages/recuperasenha.html'

        }).

        when('/Cadastro', {
            templateUrl: 'app/pages/cadastrousuario.html'
        }).

        when('/ConfigAnalise', {
            templateUrl: 'app/pages/configanalise.html'
        }).

        when('/Login', {
            templateUrl: 'app/pages/login.html',
            controller: 'LoginController',
            controllerAs: 'LoginCtrl'

        }).
        otherwise({
            redirectTo: '/Login'
        });

      $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    })
