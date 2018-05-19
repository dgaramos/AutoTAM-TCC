'use strict';

services.factory('QuestionarioService', ['$http', '$q', function($http, $q) {
    return {
        saveQuestionario: function (questionario) {
            return $http.post(__env.apiUrl + '/questionario/', questionario)
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
    }
}]);