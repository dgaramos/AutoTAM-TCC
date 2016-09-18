/**
 * Created by Danilo on 9/17/2016.
 */

'use strict';

myApp.factory('AnaliseService', function($http, $q){

    return {
        fetchAllAnalises: function() {
            return $http.get('http://localhost:8080/usuario/permissoes/')
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while fetching usuarios');
                        return $q.reject(errResponse);
                    }
                );
        }
    }
})
