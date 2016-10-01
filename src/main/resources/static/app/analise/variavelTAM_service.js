/**
 * Created by Danilo on 9/29/2016.
 */
'use strict';

services.factory('VariavelTAMService', [ '$http', '$q', function($http, $q) {
    return {
        addVariavelToAnalise: function (idAnalise, variavel) {
            return $http.post(__env.apiUrl + '/analise/variavel/' + idAnalise, variavel)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Erro ao criar Análise');
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
                        console.error('Erro ao atualizar Análise');
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
                        console.error('Erro ao listar todas as Variaveis de uma Análise');
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
                        console.error('Erro ao apagar Variável');
                        return $q.reject(errResponse);
                    }
                );
        }

    }
}]);
