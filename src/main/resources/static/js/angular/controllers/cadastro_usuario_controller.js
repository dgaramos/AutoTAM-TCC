/**
 * Created by Danilo on 9/7/2016.
 */
'use strict';

myApp.controller("CadastroUsuarioController", function(UsuarioService, $location) {
    var model = this;

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
                    model.message.box = true;
                    model.message.message = "Já existe um usuário cadastrado com esse e-mail, por favor cadastre-se com outro e-mail válido";
                })
        } else {
            model.message.box = true;
            model.message.message = "Ainda existem campos inválidos no formulário, preencha-os e tente novamente";
        }
    };

});