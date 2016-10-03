
'use strict';

services.factory('AnaliseService', ['$http', '$q', function($http, $q){

    return {

        createAnalise: function (analise) {
            return $http.post(__env.apiUrl + '/analise/', analise)
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

        updateAnalise: function (analise, idAnalise) {
            return $http.put(__env.apiUrl + '/analise/' + idAnalise, analise)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Erro ao atualizar Analise');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteAnalise: function (idAnalise) {
            return $http.delete(__env.apiUrl + '/analise/' + idAnalise)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Erro ao deletar Análise');
                        return $q.reject(errResponse);
                    }
                );
        }
    }

}]);
