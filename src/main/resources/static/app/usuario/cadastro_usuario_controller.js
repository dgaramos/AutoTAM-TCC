/**
 * @author Danilo
 */
'use strict';

controllers.controller("CadastroUsuarioController", ['UsuarioService', '$location', '$scope', '$rootScope',
    function(UsuarioService, $location, $scope, $rootScope) {
    var self = this;

    self.message = { box: false, message:""};

    self.usuario = {
        nome: "",
        email: "",
        senha: "",
        confirmaSenha: ""
    };

    self.reset = function(){
        self.message = { box: false, message:""};
        self.usuario = {
            nome: "",
            email: "",
            senha: "",
            confirmaSenha: ""
        };
    };

    self.submit = function(isValid) {
        if (isValid) {
            self.usuario = {nome: self.usuario.nome, email: self.usuario.email, senha: self.usuario.senha};
            UsuarioService.createUsuario(self.usuario)
                .then(
                    function (response) {
                        console.log(response);
                        $location.path("/Login");
                    }
                )
                .catch(
                    function(errResponse){
                        console.error(errResponse);
                        $scope.errorBox = 'alert alert-danger';
                        self.message.box = true;
                        self.message.message = "Já existe um usuário cadastrado com esse e-mail, por favor cadastre-se com outro e-mail válido";
                    }
                )
        } else {
            $scope.errorBox = 'alert alert-warning';
            self.message.box = true;
            self.message.message = "Ainda existem campos inválidos no formulário, preencha-os e tente novamente";
        }
    };

    self.recoverPassword = function(){
        UsuarioService.recoverPassword(self.usuario.email)
            .then(
                function (response) {
                    console.log(response);
                    $scope.errorBox = 'alert alert-success';
                    self.message.box = true;
                    self.message.message = "Um e-mail com sua senha cadastrada acabou de ser enviado, por favor cheque sua caixa de entrada";
                }
            )
            .catch(
                function(errResponse){
                    console.error(errResponse);
                    $scope.errorBox = 'alert alert-danger';
                    self.message.box = true;
                    self.message.message = "Não existe nenhum usuário cadastrado com esse email no sistema";
                }
            )
    };

    self.changePassword = function(isValid){
        if(isValid){
            $rootScope.loggedUsuario.senha = self.usuario.senha;
            UsuarioService.updateUsuario($rootScope.loggedUsuario, $rootScope.loggedUsuario.idUsuario);
            $scope.errorBox = 'alert alert-success';
            self.message.box = true;
            self.message.message = "Sua senha foi alterada com sucesso!";
        } else {
            $scope.errorBox = 'alert alert-warning';
            self.message.box = true;
            self.message.message = "Ainda existem campos inválidos no formulário, preencha-os e tente novamente";
        }
    };

    self.updateProfile = function(){
        if(self.usuario.nome == "" && self.usuario.email == ""){
            $scope.errorBox = 'alert alert-danger';
            self.message.box = true;
            self.message.message = "Você não alterou seus dados!";
        }else if(self.usuario.nome == ""){
            $rootScope.loggedUsuario.email = self.usuario.email;
            UsuarioService.updateUsuario($rootScope.loggedUsuario, $rootScope.loggedUsuario.idUsuario)
                .then(
                    function (response) {
                        console.log(response);
                        $scope.errorBox = 'alert alert-success';
                        self.message.box = true;
                        self.message.message = "Seu email foi alterado com sucesso";
                    }
                )
                .catch(
                    function(errResponse){
                        console.error(errResponse);
                        $scope.errorBox = 'alert alert-danger';
                        self.message.box = true;
                        self.message.message = "Já existe um usuário cadastrado com esse e-mail, por favor cadastre-se com outro e-mail válido";
                    }
                )
        }else if(self.usuario.email == ""){
            $rootScope.loggedUsuario.nome = self.usuario.nome;
            UsuarioService.updateUsuario($rootScope.loggedUsuario, $rootScope.loggedUsuario.idUsuario)
                .then(
                    function (response) {
                        console.log(response);
                        $scope.errorBox = 'alert alert-success';
                        self.message.box = true;
                        self.message.message = "Seu nome foi alterado com sucesso!";
                        $rootScope.loggedUser();
                        console.log($rootScope.loggedUsuario);
                    }
                )
                .catch(
                    function(errResponse){
                        console.error(errResponse);
                        $scope.errorBox = 'alert alert-danger';
                        self.message.box = true;
                        self.message.message = "Não foi possivel enviar um email com seus dados novos, mas seus dados foram alterados";
                    }
                )
        }else{
            $rootScope.loggedUsuario.email = self.usuario.email;
            $rootScope.loggedUsuario.nome = self.usuario.nome;
            UsuarioService.updateUsuario($rootScope.loggedUsuario, $rootScope.loggedUsuario.idUsuario)
                .then(
                    function (response) {
                        console.log(response);
                        $scope.errorBox = 'alert alert-success';
                        self.message.box = true;
                        self.message.message = "Seus dados foram alterados com sucesso!";
                        $rootScope.loggedUser();
                    }
                )
                .catch(
                    function(errResponse){
                        console.error(errResponse);
                        $scope.errorBox = 'alert alert-danger';
                        self.message.box = true;
                        self.message.message = "Já existe um usuário cadastrado com esse e-mail, por favor cadastre-se com outro e-mail válido";
                    }
                )
        }
    };
}]);