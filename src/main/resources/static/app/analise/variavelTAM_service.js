/**
 * Created by Danilo on 9/29/2016.
 */
'use strict';

services.factory('VariavelTAMService', function($http, $q) {
    return {
        addVariavelToAnalise: function (idAnalise, variavel) {
            return $http.post(__env.apiUrl + '/variavelTAM/' + idAnalise, variavel)
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

        updateVariavelFromAnalise: function (idAnalise, variavel) {
            return $http.put(__env.apiUrl + '/variavelTAM/' + idAnalise, variavel)
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

        fetchAllVariaveisFromAnalise: function (idAnalise) {
            return $http.get(__env.apiUrl + '/variavelTAM/' + idAnalise)
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

        deleteVariavel: function (idVariavel) {
            return $http.delete(__env.apiUrl + '/variavelTAM/' + idVariavel)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting variavel');
                        return $q.reject(errResponse);
                    }
                );
        }

    }
});
