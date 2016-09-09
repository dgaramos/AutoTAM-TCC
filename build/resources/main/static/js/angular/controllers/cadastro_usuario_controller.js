/**
 * Created by Danilo on 9/7/2016.
 */
'use strict';

myApp.controller("CadastroUsuarioController", function(UsuarioService, $location, $scope) {
    var model = this;
    var self = this;
    model.message = { box: false, message:""};

    model.usuario = {
        nome: "",
        email: "",
        senha: "",
        confirmaSenha: ""
    };
    model.submit = function(isValid) {
        if (isValid) {
            model.usuario = {nome: model.usuario.nome, email: model.usuario.email, senha: model.usuario.senha};
            UsuarioService.createUsuario(model.usuario)
                .then(function (response) {
                    $location.path("/Login");
                })
                .catch(function(errResponse){
                    $scope.errorBox = 'alert alert-danger';
                    model.message.box = true;
                    model.message.message = "Já existe um usuário cadastrado com esse e-mail, por favor cadastre-se com outro e-mail válido";
                })
        } else {
            $scope.errorBox = 'alert alert-warning';
            model.message.box = true;
            model.message.message = "Ainda existem campos inválidos no formulário, preencha-os e tente novamente";
        }
    };

    model.recoverPassword = function(){
        UsuarioService.recoverPassword(model.usuario.email)
            .then(function (response) {
                $scope.errorBox = 'alert alert-success';
                model.message.box = true;
                model.message.message = "Um e-mail com sua senha cadastrada acabou de ser enviado, por favor cheque sua caixa de entrada";
                }
            ).catch(function(errResponse){
                $scope.errorBox = 'alert alert-danger';
                model.message.box = true;
                model.message.message = "Não existe nenhum usuário com esse email";
        })
    };

});