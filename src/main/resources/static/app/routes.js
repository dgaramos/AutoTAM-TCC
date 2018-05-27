'use strict';

myApp.config(function ($routeProvider, $httpProvider){
        $routeProvider.

        when('', {
            resolve: {
                "checkLogged":function($rootScope,$location){
                    if($rootScope.authenticated){
                        $location.path('/Inicial');
                    }else{
                        $location.path('/Login');
                    }
                }
            }
        }).

        when('/Inicial', {
          templateUrl: '/app/pages/inicial.html',
            resolve: {
              "checkLogged":function($rootScope,$location){
                  if($rootScope.authenticated){
                      $location.path('/Inicial');
                  }else{
                      $location.path('/Login');
                  }
              }
            }
        }).

        when('/RecuperaSenha', {
            templateUrl: '/app/pages/recuperasenha.html'

        }).

        when('/Cadastro', {
            templateUrl: 'app/pages/cadastrousuario.html'
        }).

        when('/Login', {
            templateUrl: 'app/pages/login.html',
            controller: 'LoginController',
            controllerAs: 'LoginCtrl',
            resolve: {
                "checkLogged":function($rootScope,$location){
                    if($rootScope.authenticated){
                        $location.path('/Inicial');
                    }
                }
            }
        }).
        otherwise({
            redirectTo: '/Inicial'
        });

      $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    });
