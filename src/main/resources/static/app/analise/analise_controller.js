/**
 * Created by Danilo on 9/17/2016.
 */
'use strict';

controllers.controller('AnaliseController',
    ['$rootScope', '$scope', '$window', 'Global', 'AnaliseService', 'PermissaoService', 'UsuarioService', 'VariavelTAMService' ,
        function($rootScope, $scope, $window, Global, AnaliseService, PermissaoService, UsuarioService, VariavelTAMService) {

    var self = this;

            /**
             * Declarações de variáveis
             *
             */

    self.permissao = {idPermissao: null,
        usuario: {idUsuario: null, nome: '', email: '', senha: ''},
        analise: {idAnalise: null, nome: '', objetoDeAnalise: '',
            variaveis:[{idVariavel: null, nomeVariavel: 'Facilidade de Uso Percebida', variavelPadrao: true, nota: ''},
                        {idVariavel: null, nomeVariavel: 'Utilidade Percebida', variavelPadrao: true, nota: ''}],
             status: ''},
        testador: false, administrador: false};

    self.permissoes = [];

    self.analiseForm = {criaAnalise: false, variavelExtra: false};

    self.variavel = {idVariavel: null, nomeVariavel: '', variavelPadrao: false, nota: ''};

            /**
             * Funções de requisição
             *
             */

    self.fetchAllAnalises = function(){
        PermissaoService.fetchAllPermissoesFromUsuario()
            .then(
                function(d) {
                    self.permissoes = d;
                })
            .catch(
                function(errResponse){
                    console.error('Erro ao buscar Analises' + errResponse);
                }
            );
    };

    self.fetchAllAnalises();

    self.createAnalise = function(analise){
        AnaliseService.createAnalise(analise)
            .then(
                function (response) {
                    console.log('Salvando nova Analise: ' + self.permissao.analise);
                    console.log(response);
                    self.fetchAllAnalises();
                })
            .catch(
                function(errResponse){
                    console.error('Erro ao criar Analise.' + errResponse);
                }
            );
    };

    self.updateAnalise = function(analise, idAnalise){
        AnaliseService.updateAnalise(analise, idAnalise)
            .then(
                function (response) {
                    console.log('Analise a ser atualizada: '+ self.permissao.analise.nome);
                    console.log(response);
                    self.fetchAllAnalises();
            }).catch(
                function(errResponse){
                    console.error('Erro ao atualizar Análise. '+ errResponse);
        })
    };

    self.deleteAnalise = function(idAnalise){
        AnaliseService.deleteAnalise(idAnalise)
            .then(
            function(d){
                console.log('Analise a ser apagada: ' + idAnalise);
                console.log(d);
                self.fetchAllAnalises(idAnalise);
                self.reset();
                Global.fechaModal('#deleteAnaliseModal');
            },
            function(errResponse){
                console.error('Erro ao apagar Análise ' + errResponse);
            }
        );
    };

            /**
             * Funções de ação
             */

    self.reset = function(){
        self.permissao = {idPermissao: null,
            usuario: {idUsuario: null, nome: '', email: '', senha: ''},
            analise: {idAnalise: null, nome: '', objetoDeAnalise: '',
                variaveis:[{idVariavel: null, nomeVariavel: 'Facilidade de Uso Percebida',variavelPadrao: true, nota: ''},
                            {idVariavel: null, nomeVariavel: 'Utilidade Percebida',variavelPadrao: true, nota: ''}],
                status: ''},
            testador: false, administrador: false};
            self.variavel = {idVariavel: null, nomeVariavel: '',variavelPadrao: false, nota: ''};
            self.analiseForm.criaAnalise = false;
            self.analiseForm.variavelExtra = false;
    };

    self.selectAnalise= function(analise){
        self.permissao.analise = angular.copy(analise);
    };

    self.submit = function() {
        if(self.permissao.analise.idAnalise===null){
            self.createAnalise(self.permissao.analise);
            self.reset();
        }else{
            self.updateAnalise(self.permissao.analise, self.permissao.analise.idAnalise);
            self.reset();
        }
    };

    self.editAnalise = function(analise){
        self.selectAnalise(analise);
        self.criaAnalise();
    };

            /**
             * Funções de Janela
             *
             */

    self.criaAnalise = function(){
        self.analiseForm.criaAnalise = true;
        $window.scrollTo(0, 0);
    };

    self.analiseFormAbreVariavelExtra = function(){
        self.analiseForm.variavelExtra = true;
    };

    self.analiseFormFechaVariavelExtra = function(){
        self.analiseForm.variavelExtra = false;
        self.variavel = {idVariavel: null, nomeVariavel: '', variavelPadrao: false, nota: ''};
    };

    self.analiseFormRemoveVariavel = function(nomeVariavel){
        console.log('Variavel a ser removida: ' + nomeVariavel);
        for(var i = 0; i < self.permissao.analise.variaveis.length; i++){
            if(self.permissao.analise.variaveis[i].nomeVariavel === nomeVariavel) {
                self.permissao.analise.variaveis.splice(i, 1);
                break;
            }
        }
    };

    self.analiseFormAdicionaVariavelExtra = function(){
        self.permissao.analise.variaveis.push(self.variavel);
        self.analiseForm.variavelExtra = false;
        self.variavel = {idVariavel: null, nomeVariavel: '', nota: ''};
    };

            /**
             * Funções de manipulação de Variável TAM
             *
             */

    self.fetchAllVariaveisFromAnalise = function(idAnalise){
        VariavelTAMService.fetchAllVariaveisFromAnalise(idAnalise)
            .then(
                function(d) {
                    for(var i = 0; i < self.permissoes.length; i++){
                        if(self.permissoes[i].analise.idAnalise === idAnalise) {
                            self.permissoes[i].analise.variaveis = d;
                            break;
                        }
                    }
                })
            .catch(
                function(errResponse){
                    console.error('Erro ao encontrar Variáveis da Análise' + errResponse);
                }
            );
    };

    self.variavelToAnalise = function (idAnalise, variavel){
        if(variavel.idVariavel===null) {
            VariavelTAMService.addVariavelToAnalise(idAnalise, variavel)
                .then(
                    function (d) {
                        console.log(d);
                        self.fetchAllVariaveisFromAnalise(idAnalise);
                        self.reset();
                        Global.fechaModal('#criaVariavelModal');
                    },
                    function (errResponse) {
                        console.error('Erro ao adicionar Variável a Análise:' + errResponse);
                    }
                )
        }else{
            VariavelTAMService.updateVariavelFromAnalise(idAnalise, variavel)
                .then(
                    function (d) {
                        console.log(d);
                        self.fetchAllVariaveisFromAnalise(idAnalise);
                        self.reset();
                        Global.fechaModal('#criaVariavelModal');
                    },
                    function (errResponse) {
                        console.error('Erro ao atualizar Variável ' + errResponse);
                    }
                )
        }
    };

    self.selectVariavel = function (variavel, analise){
        self.variavel = angular.copy(variavel);
        self.permissao.analise = angular.copy(analise);

    };

    self.deleteVariavel = function (idVariavel, idAnalise){
        VariavelTAMService.deleteVariavel(idVariavel)
            .then(
                function(d){
                    console.log(d);
                    self.fetchAllVariaveisFromAnalise(idAnalise);
                    self.reset();
                    Global.fechaModal('#deleteVariavelModal');
                },
                function(errResponse){
                    console.error('Erro ao deletar Variável ' + errResponse);
                }
            );
    };


            /**
             * Funções de gerenciamento de permissão
             *
             */

    self.permissaoConvite = {idPermissao: null,
        usuario: {idUsuario: null, nome: '', email: '', senha: ''},
        analise: self.permissao.analise ,
        testador: false, administrador: false};
    self.permissoesConvite = [];
    self.erroPermissao = false;

    self.resetPermissao = function(){
        self.permissaoConvite = {idPermissao: null,
            usuario: {idUsuario: null, nome: '', email: '', senha: ''},
            analise: self.permissao.analise ,
            testador: false, administrador: false};
        self.erroPermissao = false;
    }

    self.fetchAllPermissoesFromAnalise = function(analise){
        self.permissaoConvite.analise = analise;
        PermissaoService.fetchAllPermissoesFromAnalise(analise.idAnalise)
            .then(
                function(p){
                    console.log(p);
                    self.permissoesConvite = p;
                    console.log(self.permissoesConvite);
                })
            .catch(
                function(errResponse){
                    console.log("Analise sem permissões" + errResponse)
                }
            );
    };

    self.savePermissao = function (permissao){
        PermissaoService.addPermissaoToAnalise(permissao)
            .then(function (response) {
                console.log(response);
                permissao.usuario = {idUsuario: null, nome: '', email: '', senha: ''};
                permissao.testador = false;
                permissao.administrador = false;
            })
            .catch(
                function(errResponse){
                    console.error('Erro ao criar Permissao.' + errResponse);
                }
            );
    };

    self.adicionaPermissao = function(analise){
        self.erroPermissao = false;
        if(self.permissaoConvite.usuario.email == $rootScope.loggedUsuario.email){
            console.error('Você está tentando adicionar o usuario logado');
            self.erroPermissao = true;
        } else{
            UsuarioService.fetchUsuarioByEmail(self.permissaoConvite.usuario.email)
                .then(
                    function(u) {
                        for (var i = 0; i < self.permissoesConvite.length; i++) {
                            if (self.permissoesConvite[i].usuario.idUsuario === u.idUsuario) {
                                self.erroPermissao = true;
                                console.error('Usuario já possui permissão');
                            }
                        }
                        if(self.erroPermissao === false){
                            self.permissaoConvite.usuario = u;
                            self.savePermissao(self.permissaoConvite)
                                .then(
                                    function(p){
                                        self.fetchAllPermissoesFromAnalise(analise);
                                    },
                                    function (errResponse) {
                                        console.log(errResponse);
                                    }
                                )
                        }
                    },
                    function(errResponse){
                        console.error('Usuario não encontrado' + errResponse);
                        self.erroPermissao = true;
                    }
                )
        }

    };

    self.selectPermissao = function(permissao){
        self.permissaoConvite = angular.copy(permissao);
    };

    self.updatePermissao = function(permissao, analise){
        PermissaoService.updatePermissao(permissao, permissao.idPermissao)
            .then(
                function(d){
                    console.log(d);
                    self.fetchAllPermissoesFromAnalise(analise);
                    self.resetPermissao();
                })
            .catch(
                function(errResponse){
                    console.error('Erro ao atualizar Permissao' + errResponse);
                }
            );
    };

    self.submitPermissao = function(permissao){
        if(permissao.idPermissao === null){
            self.adicionaPermissao(permissao.analise);
        }else{
            self.updatePermissao(permissao, permissao.analise);
        }
    }

    self.deletePermissao = function (idPermissao, analise){
        PermissaoService.deletePermissao(idPermissao)
            .then(
                function(d){
                    console.log(d);
                    self.fetchAllPermissoesFromAnalise(analise);
                    self.resetPermissao();
                })
            .catch(
                function(errResponse){
                    console.error('Erro ao deletar Permissão ' + errResponse);
                }
            );
    };

}]);
