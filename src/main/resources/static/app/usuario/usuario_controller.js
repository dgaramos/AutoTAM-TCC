'use strict';

controllers.controller('UsuarioController', function($scope, UsuarioService) {
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
                    console.error('Error while fetching Usuario');
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
                console.error('Error while creating Usuario.' + errResponse);
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
                    console.error('Error while updating Usuario.'+ errResponse);
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
                    console.error('Error while deleting Usuario.');
                }
            );
    };

    self.fetchAllUsuarios();

    self.submit = function() {
        if(self.usuario.idUsuario===null){
            console.log('Saving New Usuario', self.usuario);
            self.createUsuario(self.usuario);
        }else{
            self.updateUsuario(self.usuario, self.usuario.idUsuario);
            console.log('User updated with email ', self.usuario.email);
        }
    };

    self.edit = function(idUsuario){
        console.log('Usuario to be edited', idUsuario);
        for(var i = 0; i < self.usuarios.length; i++){
            if(self.usuarios[i].idUsuario === idUsuario) {
                self.usuario = angular.copy(self.usuarios[i]);
                break;
            }
        }
    };

    self.remove = function(idUsuario){
        console.log('Usuario id to be deleted', idUsuario);
        if(self.usuario.idUsuario === idUsuario) {//clean form if the user to be deleted is shown there.
            self.reset();
        }
        self.deleteUsuario(idUsuario);
    };


    self.reset = function(){
        self.usuario={idUsuario:null,nome:'',email:'',senha:''};
        self.message.box = false;
        $scope.myForm.$setPristine(); //reset Form
    };

});