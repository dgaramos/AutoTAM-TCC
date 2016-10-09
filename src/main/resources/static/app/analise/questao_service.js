'use strict';

services.factory('QuestaoService', [ '$http', '$q', function($http, $q) {
    return {

        fetchAllQuestoesFromVariavel: function (idVariavel) {
            return $http.get(__env.apiUrl + '/questao/' + idVariavel)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Erro ao listar todas as Questões de uma Variável');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteQuestao: function (idQuestao) {
            return $http.delete(__env.apiUrl + '/questao/' + idQuestao)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Erro ao apagar Questão');
                        return $q.reject(errResponse);
                    }
                );
        }
    }
}]);