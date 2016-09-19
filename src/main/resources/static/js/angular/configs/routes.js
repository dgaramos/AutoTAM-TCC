'use strict';

myApp.config(function ($routeProvider, $httpProvider){
        $routeProvider.
        when('/Inicial', {
          templateUrl: '/html/pages/inicial.html'

        }).

        when('/RecuperaSenha', {
            templateUrl: '/html/pages/recuperasenha.html'

        }).

        when('/Cadastro', {
            templateUrl: 'html/pages/cadastrousuario.html'
        }).

        when('/ConfigAnalise', {
            templateUrl: 'html/pages/configanalise.html'
        }).

        when('/Login', {
            templateUrl: '/html/pages/login.html',
            controller: 'LoginController',
            controllerAs: 'LoginCtrl'

        }).
        otherwise({
            redirectTo: '/Login'
        });

      $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    })
