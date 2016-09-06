'use strict';

myApp.controller('LoginController', function($rootScope, $http, $location, $route, UsuarioService) {

    var self = this;

    self.loggedUsuario = {idUsuario:null,nome:'',email:'',senha:''};
    self.tab = function(route) {
      return $route.current && route === $route.current.controller;
    };

    var authenticate = function(credentials, callback) {

          var headers = credentials ? {
              authorization : "Basic "
              + btoa(credentials.username + ":"
                  + credentials.password)
          } : {};

          $http.get('http://localhost:8080/userLogin', {
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

    var loggedUser = function(){
        $http.get('http://localhost:8080/userLogin')
            .then(
                function(response) {
                    UsuarioService.fetchUsuarioByEmail(response.data.name)
                        .then(
                            function (d) {
                                console.log(d)
                                self.loggedUsuario = d;
                                console.log(self.loggedUsuario)

                            },
                            function (errResponse) {
                                console.error('Error while fetching logged Usuario');
                            }
                        );
                })
    }
    loggedUser();
      self.credentials = {};
      self.login = function() {
          authenticate(self.credentials, function(authenticated) {
              if (authenticated) {
                  loggedUser();
                  console.log("Login succeeded, logged usuario: " + self.credentials.username)
                  $location.path("/ListarUsuarios");
                  self.error = false;
                  $rootScope.authenticated = true;
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
              self.loggedUsuario={idUsuario:null,nome:'',email:'',senha:''};
              $location.path("/Login");
          });
      }

  })
