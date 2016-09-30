/**
 * Created by Danilo on 9/29/2016.
 */
'use strict';

services.factory('PermissaoService', function($http, $q){
    return {

        fetchAllPermissoesFromUsuario: function () {
            return $http.get(__env.apiUrl + '/permissao')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while fetching permissoes');
                        return $q.reject(errResponse);
                    }
                );
        },

        fetchAllPermissoesFromAnalise: function (idAnalise) {
            return $http.get(__env.apiUrl + '/permissao/' + idAnalise)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while fetching permissoes');
                        return $q.reject(errResponse);
                    }
                );
        },
        addPermissaoToAnalise: function (permissao) {
            return $http.post(__env.apiUrl + '/permissao/', permissao)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while updating analise');
                        return $q.reject(errResponse);
                    }
                );
        },

        deletePermissao: function (idPermissao) {
            return $http.delete(__env.apiUrl + '/permissao/' + idPermissao)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting permissao');
                        return $q.reject(errResponse);
                    }
                );
        }
    }
});