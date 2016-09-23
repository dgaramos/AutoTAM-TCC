'use strict';

controllers.controller('LoginController', function($rootScope, $http, $location, $route, UsuarioService) {

    var self = this;

    $rootScope.loggedUsuario = {idUsuario:null,nome:'',email:'',senha:''};

    self.tab = function(route) {
      return $route.current && route === $route.current.controller;
    };

    var authenticate = function(credentials, callback) {

        var headers = credentials ? {
            authorization : "Basic "
            + btoa(credentials.username + ":"
                + credentials.password)
        } : {};

        $http.get(__env.apiUrl + '/userLogin', {
            headers : headers
        }).then(function(response) {
            if (response.data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback($rootScope.authenticated);
        }, function() {
            $rootScope.authenticated = false;
            callback && callback(false);
        });

    }

    authenticate();

    $rootScope.loggedUser = function(){
        UsuarioService.fetchLoggedUser()
            .then(
                function (d) {
                    $rootScope.loggedUsuario = d;
                },
                function (errResponse) {
                    console.error('Error while fetching logged Usuario');
                }
            )
    }
    $rootScope.loggedUser();
    self.credentials = {};
    self.login = function() {
        authenticate(self.credentials, function(authenticated) {
            if (authenticated) {
                console.log("Login succeeded, logged usuario: " + self.credentials.username)
                $location.path("/Inicial");
                self.error = false;
                $rootScope.authenticated = true;
                $rootScope.loggedUser();
            } else {
                console.log("Login failed")
                $location.path("/Login");
                self.error = true;
                $rootScope.authenticated = false;
            }
        })
    };

    self.logout = function() {
        $http.post('logout', {}).finally(function() {
            $rootScope.authenticated = false;
            $rootScope.loggedUsuario={idUsuario:null,nome:'',email:'',senha:''};
            $location.path("/Login");
        });
    }

})
