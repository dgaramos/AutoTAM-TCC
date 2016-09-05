'use strict';

myApp.factory('UsuarioService', function($http, $q){

    return {

        fetchAllUsuarios: function() {
            return $http.get('http://localhost:8080/usuario/')
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while fetching usuarios');
                        return $q.reject(errResponse);
                    }
                );
        },

        createUsuario: function(usuario){
            return $http.post('http://localhost:8080/usuario/', usuario)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while creating user');
                        return $q.reject(errResponse);
                    }
                );
        },

        updateUsuario: function(usuario, idUsuario){
            return $http.put('http://localhost:8080/usuario/'+ idUsuario, usuario)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while updating usuario');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteUsuario: function(idUsuario){
            return $http.delete('http://localhost:8080/usuario/'+ idUsuario)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while deleting usuario');
                        return $q.reject(errResponse);
                    }
                );
        }

    };

});