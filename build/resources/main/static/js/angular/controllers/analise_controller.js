/**
 * Created by Danilo on 9/17/2016.
 */

myApp.controller('AnaliseController', function($scope, AnaliseService, UsuarioService) {
    var self = this;
    self.usuario = {idUsuario: null, nome: '', email: '', senha: ''};
    self.usuarios = [];
    self.permissao = {idPermissao: null,
        usuario: {idUsuario: null, nome: '', email: '', senha: ''},
        analise: {idAnalise: null, nome: '', objetoDeAnalise: '', status: ''},
        testador: false, administrador: false};
    self.permissoes = [];




    self.fetchAllAnalises = function(){
        AnaliseService.fetchAllAnalises()
            .then(
                function(d) {
                    self.permissoes = d;
                },
                function(errResponse){
                    console.error('Error while fetching Analises');
                }
            );
    };
    self.fetchAllAnalises();
})