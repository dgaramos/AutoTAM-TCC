
'use strict';

services.factory('OpcaoDeObjetoService', [ '$http', '$q', function($http, $q) {
    return {
        addOpcaoDeObjetoToAnalise: function (idAnalise, opcaoDeObjeto) {
            return $http.post(__env.apiUrl + '/analise/opcaoDeObjeto/' + idAnalise, opcaoDeObjeto)
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

        updateOpcaoDeObjetoFromAnalise: function (idOpcaoDeObjeto, opcaoDeObjeto) {
            return $http.put(__env.apiUrl + '/opcaoDeObjeto/' + idOpcaoDeObjeto, opcaoDeObjeto)
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

        fetchAllOpcoesDeObjetoFromAnalise: function (idAnalise) {
            return $http.get(__env.apiUrl + '/opcaoDeObjeto/' + idAnalise)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Erro ao listar todas as Opções de Objeto de uma Análise');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteOpcaoDeObjeto: function (idOpcaoDeObjeto) {
            return $http.delete(__env.apiUrl + '/opcaoDeObjeto/' + idOpcaoDeObjeto)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Erro ao apagar Opção De Objeto');
                        return $q.reject(errResponse);
                    }
                );
        }

    }
}]);
