'use strict';

controllers.controller('UsuarioController', [ '$scope', 'UsuarioService',
    function($scope, UsuarioService) {

    var self = this;
    self.usuario={idUsuario:null,nome:'',email:'',senha:''};
    self.usuarios=[];
    self.email;
    self.message = {box: false, message: ''};

    self.fetchAllUsuarios = function(){
        UsuarioService.fetchAllUsuarios()
            .then(
                function(d) {
                    self.usuarios = d;
                },
                function(errResponse){
                    console.error('Error ao listar Usuários' + errResponse);
                }
            );
    };

    self.createUsuario = function(usuario){
        UsuarioService.createUsuario(usuario)
            .then(function (response) {
                console.log(response);
                self.fetchAllUsuarios();
                self.reset();
            })
            .catch(function(errResponse){
                console.error('Erro ao criar Usuario.' + errResponse);
                $scope.errorBox = 'alert alert-danger';
                self.message.box = true;
                self.message.message = "Já existe um usuário cadastrado com esse e-mail, por favor cadastre-se com outro e-mail válido";
            })
    };

    self.updateUsuario = function(usuario, idUsuario){
        UsuarioService.updateUsuario(usuario, idUsuario)
            .then(function (response) {
                console.log(response);
                self.fetchAllUsuarios();
                self.reset();
            }).catch(function(errResponse){
                    console.error('Erro ao atualizar Usuario.'+ errResponse);
                    $scope.errorBox = 'alert alert-danger';
                    self.message.box = true;
                    self.message.message = "Já existe um usuário cadastrado com esse e-mail, por favor cadastre-se com outro e-mail válido";
            })
    };

    self.deleteUsuario = function(idUsuario){
        UsuarioService.deleteUsuario(idUsuario)
            .then(
                self.fetchAllUsuarios,
                function(errResponse){
                    console.error('Erro ao apagar Usuario.' + errResponse);
                }
            );
    };

    self.fetchAllUsuarios();

    self.submit = function() {
        if(self.usuario.idUsuario===null){
            console.log('Registrando novo Usuario', self.usuario);
            self.createUsuario(self.usuario);
        }else{
            self.updateUsuario(self.usuario, self.usuario.idUsuario);
            console.log('Usuário a ser atualizado: ', self.usuario.email);
        }
    };

    self.edit = function(idUsuario){
        console.log('Usuario a ser atualizado: ', idUsuario);
        for(var i = 0; i < self.usuarios.length; i++){
            if(self.usuarios[i].idUsuario === idUsuario) {
                self.usuario = angular.copy(self.usuarios[i]);
                break;
            }
        }
    };

    self.remove = function(idUsuario){
        console.log('Usuario a ser deletado: ', idUsuario);
        if(self.usuario.idUsuario === idUsuario) {
            self.reset();
        }
        self.deleteUsuario(idUsuario);
    };


    self.reset = function(){
        self.usuario={idUsuario:null,nome:'',email:'',senha:''};
        self.message.box = false;
        $scope.myForm.$setPristine();
    };

}]);