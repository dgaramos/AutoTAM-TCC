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
        self.senhaAtual = "";
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
        UsuarioService.recoverPassword(self.usuario.email)
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
            if(self.senhaAtual == self.usuario.senha){
                $scope.errorBox = 'alert alert-warning';
                self.message.box = true;
                self.message.message = "Sua nova senha não pode ser a mesma da senha atual!";
            }else if($rootScope.loggedUsuario.senha == self.senhaAtual){
                $rootScope.loggedUsuario.senha = self.usuario.senha;
                UsuarioService.updateUsuario($rootScope.loggedUsuario, $rootScope.loggedUsuario.idUsuario);
                $scope.errorBox = 'alert alert-success';
                self.message.box = true;
                self.message.message = "Sua senha foi alterada com sucesso!";
            }else{
                $scope.errorBox = 'alert alert-danger';
                self.message.box = true;
                self.message.message = "A essa não é sua senha atual";
            }
        }else {
            $scope.errorBox = 'alert alert-warning';
            self.message.box = true;
            self.message.message = "Ainda existem campos inválidos no formulário, preencha-os e tente novamente";
        }
    }

    self.updateProfile = function(){
        if(self.usuario.nome == "" && self.usuario.email == ""){
            $scope.errorBox = 'alert alert-danger';
            self.message.box = true;
            self.message.message = "Você não alterou seus dados!";
        }else if(self.usuario.nome == ""){
            $rootScope.loggedUsuario.email = self.usuario.email;
            UsuarioService.updateUsuario($rootScope.loggedUsuario, $rootScope.loggedUsuario.idUsuario)
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
            $rootScope.loggedUsuario.nome = self.usuario.nome;
            UsuarioService.updateUsuario($rootScope.loggedUsuario, $rootScope.loggedUsuario.idUsuario)
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
            $rootScope.loggedUsuario.email = self.usuario.email;
            $rootScope.loggedUsuario.nome = self.usuario.nome;
            UsuarioService.updateUsuario($rootScope.loggedUsuario, $rootScope.loggedUsuario.idUsuario)
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
    }
});