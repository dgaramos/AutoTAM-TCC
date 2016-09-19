/**
 * Created by Danilo on 9/17/2016.
 */

'use strict';

myApp.factory('AnaliseService', function($http, $q){

    return {
        fetchAllAnalises: function () {
            return $http.get('http://localhost:8080/usuario/permissoes/')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while fetching usuarios');
                        return $q.reject(errResponse);
                    }
                );
        },

        createAnalise: function (analise) {
            return $http.post('http://localhost:8080/analise/', analise)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while creating analise');
                        return $q.reject(errResponse);
                    }
                );
        },

        updateAnalise: function (analise, idAnalise) {
            return $http.put('http://localhost:8080/usuario/' + idAnalise, analise)
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

        deleteAnalise: function (idAnalise) {
            return $http.delete('http://localhost:8080/analise/' + idAnalise)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting analise');
                        return $q.reject(errResponse);
                    }
                );
        }
    }
});
