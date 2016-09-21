/**
 * Created by Danilo on 9/7/2016.
 */
'use strict';

controllers.controller("CadastroUsuarioController", function(UsuarioService, $location, $scope, $rootScope) {
    var self = this;

    self.message = { box: false, message:""};

    self.usuario = {
        nome: "",
        email: "",
        senha: "",
        confirmaSenha: ""
    };

    self.senhaAtual = "";

    self.reset = function(){
        self.message = { box: false, message:""};
        self.usuario = {
            nome: "",
            email: "",
            senha: "",
            confirmaSenha: ""
        };
    }

    self.submit = function(isValid) {
        if (isValid) {
            self.usuario = {nome: self.usuario.nome, email: self.usuario.email, senha: self.usuario.senha};
            UsuarioService.createUsuario(self.usuario)
                .then(function (response) {
                    $location.path("/Login");
                })
                .catch(function(errResponse){
                    $scope.errorBox = 'alert alert-danger';
                    self.message.box = true;
                    self.message.message = "Já existe um usuário cadastrado com esse e-mail, por favor cadastre-se com outro e-mail válido";
                })
        } else {
            $scope.errorBox = 'alert alert-warning';
            self.message.box = true;
            self.message.message = "Ainda existem campos inválidos no formulário, preencha-os e tente novamente";
        }
    };

    self.recoverPassword = function(){
        UsuarioService.recoverPassword(model.usuario.email)
            .then(function (response) {
                $scope.errorBox = 'alert alert-success';
                self.message.box = true;
                self.message.message = "Um e-mail com sua senha cadastrada acabou de ser enviado, por favor cheque sua caixa de entrada";
                }
            ).catch(function(errResponse){
                $scope.errorBox = 'alert alert-danger';
                self.message.box = true;
                self.message.message = "Não existe nenhum usuário cadastrado com esse email no sistema";
        })
    };

    self.changePassword = function(isValid){
        if(isValid){
            if(model.senhaAtual == model.usuario.senha){
                $scope.errorBox = 'alert alert-warning';
                self.message.box = true;
                self.message.message = "Sua nova senha não pode ser a mesma da senha atual!";
            }else{
                UsuarioService.fetchLoggedUser()
                    .then(
                        function (loggedUser) {
                            console.log(model.senhaAtual);
                            if(loggedUser.senha == model.senhaAtual){
                                loggedUser.senha = model.usuario.senha;
                                UsuarioService.updateUsuario(loggedUser, loggedUser.idUsuario);
                                $scope.errorBox = 'alert alert-success';
                                self.message.box = true;
                                self.message.message = "Sua senha foi alterada com sucesso!";
                            }else{
                                $scope.errorBox = 'alert alert-danger';
                                self.message.box = true;
                                self.message.message = "A essa não é sua senha atual";
                            }
                        },
                        function (errResponse) {
                            console.error('Error while fetching logged Usuario');
                        }
                    )
            }
        }else {
            $scope.errorBox = 'alert alert-warning';
            self.message.box = true;
            self.message.message = "Ainda existem campos inválidos no formulário, preencha-os e tente novamente";
        }
    }

    self.updateProfile = function(){
        console.log($rootScope.loggedUsuario);
        UsuarioService.fetchLoggedUser()
            .then(
                function (loggedUser) {
                    if(self.usuario.nome == "" && self.usuario.email == ""){
                        $scope.errorBox = 'alert alert-danger';
                        self.message.box = true;
                        self.message.message = "Você não alterou seus dados!";
                    }else if(self.usuario.nome == ""){
                        loggedUser.email = self.usuario.email;
                        UsuarioService.updateUsuario(loggedUser, loggedUser.idUsuario)
                            .then(function (response) {
                                $scope.errorBox = 'alert alert-success';
                                self.message.box = true;
                                self.message.message = "Seu email foi alterado com sucesso";
                            })
                            .catch(function(errResponse){
                                $scope.errorBox = 'alert alert-danger';
                                self.message.box = true;
                                self.message.message = "Já existe um usuário cadastrado com esse e-mail, por favor cadastre-se com outro e-mail válido";
                            })
                    }else if(self.usuario.email == ""){
                        loggedUser.nome = self.usuario.nome;
                        UsuarioService.updateUsuario(loggedUser, loggedUser.idUsuario)
                            .then(function (response) {
                                $scope.errorBox = 'alert alert-success';
                                self.message.box = true;
                                self.message.message = "Seu nome foi alterado com sucesso!";
                                $rootScope.loggedUser();
                                console.log($rootScope.loggedUsuario);
                            })
                            .catch(function(errResponse){
                                $scope.errorBox = 'alert alert-danger';
                                self.message.box = true;
                                self.message.message = "Não foi possivel enviar um email com seus dados novos, mas seus dados foram alterados";
                            })
                    }else{
                        loggedUser.email = self.usuario.email;
                        loggedUser.nome = self.usuario.nome;
                        UsuarioService.updateUsuario(loggedUser, loggedUser.idUsuario)
                            .then(function (response) {
                                $scope.errorBox = 'alert alert-success';
                                self.message.box = true;
                                self.message.message = "Seus dados foram alterados com sucesso!";
                                $rootScope.loggedUser();
                            })
                            .catch(function(errResponse){
                                $scope.errorBox = 'alert alert-danger';
                                self.message.box = true;
                                self.message.message = "Já existe um usuário cadastrado com esse e-mail, por favor cadastre-se com outro e-mail válido";
                            })
                    }
                },
                function (errResponse) {
                    console.error('Error while fetching logged Usuario');
                }
        )
    }


});