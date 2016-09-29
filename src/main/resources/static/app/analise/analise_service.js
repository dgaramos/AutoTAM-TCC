/**
 * Created by Danilo on 9/17/2016.
 */

'use strict';

services.factory('AnaliseService', function($http, $q){

    return {
        fetchAllAnalises: function () {
            return $http.get(__env.apiUrl + '/usuario/permissoes/')
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
            return $http.post(__env.apiUrl + '/analise/', analise)
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
            return $http.put(__env.apiUrl + '/analise/' + idAnalise, analise)
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
//-----------------------------------Variavel Requisitions--------------------------------------
        addVariavelToAnalise: function (idAnalise, variavel) {
            return $http.post(__env.apiUrl + '/analise/variavel/' + idAnalise, variavel)
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
            return $http.put(__env.apiUrl + '/analise/variavelUpdate/' + idAnalise, variavel)
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
            return $http.get(__env.apiUrl + '/analise/variaveis/' + idAnalise)
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

        deleteAnalise: function (idAnalise) {
            return $http.delete(__env.apiUrl + '/analise/' + idAnalise)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting analise');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteVariavel: function (idVariavel) {
            return $http.delete(__env.apiUrl + '/analise/variavel/' + idVariavel)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting variavel');
                        return $q.reject(errResponse);
                    }
                );
        },
        //-----------------------------------Variavel Requisitions--------------------------------------
        fetchAllPermissoesFromAnalise: function (idAnalise) {
            return $http.get(__env.apiUrl + '/analise/permissoes/' + idAnalise)
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
            return $http.post(__env.apiUrl + '/analise/permissoes/', permissao)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while updating analise');
                        return $q.reject(errResponse);
                    }
                );
        }



    }

});
