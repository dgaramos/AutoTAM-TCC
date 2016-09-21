/**
 * Created by Danilo on 9/7/2016.
 */
'use strict';

controllers.controller("CadastroUsuarioController", function(UsuarioService, $location, $scope, $window) {
    var model = this;

    model.message = { box: false, message:""};

    model.usuario = {
        nome: "",
        email: "",
        senha: "",
        confirmaSenha: ""
    };

    model.senhaAtual = "";

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
                model.message.message = "Não existe nenhum usuário cadastrado com esse email no sistema";
        })
    };

    model.changePassword = function(isValid){
        if(isValid){
            if(model.senhaAtual == model.usuario.senha){
                $scope.errorBox = 'alert alert-warning';
                model.message.box = true;
                model.message.message = "Sua nova senha não pode ser a mesma da senha atual!";
            }else{
                UsuarioService.fetchLoggedUser()
                    .then(
                        function (loggedUser) {
                            console.log(model.senhaAtual);
                            if(loggedUser.senha == model.senhaAtual){
                                loggedUser.senha = model.usuario.senha;
                                UsuarioService.updateUsuario(loggedUser, loggedUser.idUsuario);
                                $scope.errorBox = 'alert alert-success';
                                model.message.box = true;
                                model.message.message = "Sua senha foi alterada com sucesso!";
                            }else{
                                $scope.errorBox = 'alert alert-danger';
                                model.message.box = true;
                                model.message.message = "A essa não é sua senha atual";
                            }
                        },
                        function (errResponse) {
                            console.error('Error while fetching logged Usuario');
                        }
                    )
            }
        }else {
            $scope.errorBox = 'alert alert-warning';
            model.message.box = true;
            model.message.message = "Ainda existem campos inválidos no formulário, preencha-os e tente novamente";
        }
    }

    model.updateProfile = function(){
        UsuarioService.fetchLoggedUser()
            .then(
                function (loggedUser) {
                    console.log(model.usuario);
                    if(model.usuario.nome == "" && model.usuario.email == ""){
                        $scope.errorBox = 'alert alert-danger';
                        model.message.box = true;
                        model.message.message = "Você não alterou seus dados!";
                    }else if(model.usuario.nome == ""){
                        loggedUser.email = model.usuario.email;
                        UsuarioService.updateUsuario(loggedUser, loggedUser.idUsuario)
                            .then(function (response) {
                                $scope.errorBox = 'alert alert-success';
                                model.message.box = true;
                                model.message.message = "Seu email foi alterado com sucesso";
                            })
                            .catch(function(errResponse){
                                $scope.errorBox = 'alert alert-danger';
                                model.message.box = true;
                                model.message.message = "Já existe um usuário cadastrado com esse e-mail, por favor cadastre-se com outro e-mail válido";
                            })
                    }else if(model.usuario.email == ""){
                        loggedUser.nome = model.usuario.nome;
                        UsuarioService.updateUsuario(loggedUser, loggedUser.idUsuario)
                            .then(function (response) {
                                $scope.errorBox = 'alert alert-success';
                                model.message.box = true;
                                model.message.message = "Seu nome foi alterado com sucesso!";
                                $window.location.reload();
                            })
                            .catch(function(errResponse){
                                $scope.errorBox = 'alert alert-danger';
                                model.message.box = true;
                                model.message.message = "Não foi possivel enviar um email com seus dados novos";
                            })
                    }else{
                        loggedUser.email = model.usuario.email;
                        loggedUser.nome = model.usuario.nome;
                        UsuarioService.updateUsuario(loggedUser, loggedUser.idUsuario)
                            .then(function (response) {
                                $scope.errorBox = 'alert alert-success';
                                model.message.box = true;
                                model.message.message = "Seus dados foram alterados com sucesso!";
                                $window.location.reload();
                            })
                            .catch(function(errResponse){
                                $scope.errorBox = 'alert alert-danger';
                                model.message.box = true;
                                model.message.message = "Já existe um usuário cadastrado com esse e-mail, por favor cadastre-se com outro e-mail válido";
                            })
                    }
                },
                function (errResponse) {
                    console.error('Error while fetching logged Usuario');
                }
        )
    }


});