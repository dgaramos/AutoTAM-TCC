'use strict';

services.factory('QuestionarioService', ['$http', '$q', function($http, $q) {
    return {
        saveQuestionario: function (idOpcaoDeObjeto, analise) {
            return $http.post(__env.apiUrl + '/questionario/' + idOpcaoDeObjeto , analise)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while saving questionario');
                        return $q.reject(errResponse);
                    }
                );
        },
        questionarioJaRespondido: function(idAnalise, idOpcaoDeObjeto){
            return $http.get(__env.apiUrl + '/questionario/' + idAnalise+ '/' + idOpcaoDeObjeto)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error');
                        return $q.reject(errResponse);
                    }
                );
        },
        quantidadeQuestionariosOpcaoDeObjeto: function(idOpcaoDeObjeto, idAnalise){
            return $http.get(__env.apiUrl + '/questionario/qntd/' + idOpcaoDeObjeto+ '/' + idAnalise)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error');
                        return $q.reject(errResponse);
                    }
                );
        }
    }
}]);