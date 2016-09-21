'use strict';

services.factory('UsuarioService', function($http, $q){

    return {

        fetchAllUsuarios: function() {
            return $http.get(__env.apiUrl + '/usuario/')
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

        fetchUsuarioByEmail: function(email) {
            return $http.get(__env.apiUrl + '/usuario/byEmail/'+ email )
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while fetching usuario');
                        return $q.reject(errResponse);
                    }
                );
        },

        createUsuario: function(usuario){
            return $http.post(__env.apiUrl + '/usuario/noauth/register/', usuario)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while creating usuario');
                        return $q.reject(errResponse);
                    }
                );
        },

        recoverPassword: function(email){
            return $http.get(__env.apiUrl + '/usuario/noauth/password/'+ email)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error recovering password');
                        return $q.reject(errResponse);
                    }
                );
        },

        updateUsuario: function(usuario, idUsuario){
            return $http.put(__env.apiUrl + '/usuario/'+ idUsuario, usuario)
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
            return $http.delete(__env.apiUrl + '/usuario/'+ idUsuario)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while deleting usuario');
                        return $q.reject(errResponse);
                    }
                );
        },

        fetchLoggedUser: function(){
            return $http.get(__env.apiUrl + '/usuario/logged')
                .then(
                    function(response){
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while fetching logged Usuario');
                        return $q.reject(errResponse);
                    })
        }

    }

});