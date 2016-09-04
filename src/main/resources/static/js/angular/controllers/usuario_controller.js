'use strict';

myApp.controller('UsuarioController', ['$scope', 'UsuarioService', function($scope, UsuarioService) {
    var self = this;
    self.usuario={idUsuario:null,nome:'',email:'',senha:''};
    self.usuarios=[];

    self.fetchAllUsuarios = function(){
        UsuarioService.fetchAllUsuarios()
            .then(
                function(d) {
                    self.usuarios = d;
                },
                function(errResponse){
                    console.error('Error while fetching Currencies');
                }
            );
    };

    self.createUsuario = function(usuario){
        UsuarioService.createUsuario(usuario)
            .then(
                self.fetchAllUsuarios,
                function(errResponse){
                    console.error('Error while creating Usuario.');
                }
            );
    };

    self.updateUsuario = function(usuario, idUsuario){
        UsuarioService.updateUsuario(usuario, idUsuario)
            .then(
                self.fetchAllUsuarios,
                function(errResponse){
                    console.error('Error while updating Usuario.');
                }
            );
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
        self.reset();
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
        $scope.myForm.$setPristine(); //reset Form
    };

}]);