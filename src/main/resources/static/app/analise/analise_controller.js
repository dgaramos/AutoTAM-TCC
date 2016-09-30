/**
 * Created by Danilo on 9/17/2016.
 */
'use strict';

controllers.controller('AnaliseController', function($rootScope, $scope, $window, AnaliseService, PermissaoService, UsuarioService, VariavelTAMService) {
    var self = this;

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

    self.fetchAllAnalises = function(){
        PermissaoService.fetchAllPermissoesFromUsuario()
            .then(
                function(d) {
                    self.permissoes = d;
                },
                function(errResponse){
                    console.error('Error while fetching Analises');
                }
            );
    };
    self.fetchAllAnalises();

    self.createAnalise = function(analise){
        console.log(analise)
        AnaliseService.createAnalise(analise)
            .then(function (response) {
                console.log(response);
                self.fetchAllAnalises();
            })
            .catch(function(errResponse){
                console.error('Error while creating Analise.' + errResponse);
            })
    };

    self.updateAnalise = function(analise, idAnalise){
        AnaliseService.updateAnalise(analise, idAnalise)
            .then(function (response) {
                console.log(response);
                self.fetchAllAnalises();
            }).catch(function(errResponse){
            console.error('Error while updating Usuario.'+ errResponse);
        })
    };


    self.submit = function() {
        if(self.permissao.analise.idAnalise===null){
            console.log('Saving New Analise', self.permissao.analise);
            self.createAnalise(self.permissao.analise);
            self.fetchAllAnalises();
            self.reset();
        }else{
            self.updateAnalise(self.permissao.analise, self.permissao.analise.idAnalise);
            console.log('Analise updated with nome ', self.permissao.analise.nome);
            self.fetchAllAnalises();
            self.reset();
        }
    };

    self.editAnalise = function(analise){
        console.log('Analise to be edited', analise.idAnalise);
        self.selectAnalise(analise);
        self.criaAnalise();
    };


    self.selectAnalise= function(analise){
        self.permissao.analise = angular.copy(analise);
    }


    self.deleteAnalise = function(idAnalise){
        console.log('Analise id to be deleted', idAnalise);
        AnaliseService.deleteAnalise(idAnalise)
            .then(
            function(d){
                self.fetchAllAnalises(idAnalise);
                self.reset();
                $('#deleteAnaliseModal').modal('hide');
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
            },
            function(errResponse){
                console.error('Error while fetching variaveis from Analise');
            }
        );

    };

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

    self.criaAnalise = function(){
        self.analiseForm.criaAnalise = true;
        $window.scrollTo(0, 0);
    };

//--------------------------------------Operações com Variáveis--------------------------------------------------------

    //--------------------------------------Durante criação da Análise--------------------------------------------------------

    self.analiseFormAbreVariavelExtra = function(){
        self.analiseForm.variavelExtra = true;
    };
    self.analiseFormFechaVariavelExtra = function(){
        self.analiseForm.variavelExtra = false;
        self.variavel = {idVariavel: null, nomeVariavel: '', variavelPadrao: false, nota: ''};
    };

    self.analiseFormRemoveVariavel = function(nomeVariavel){
        console.log('Variavel to be removed', nomeVariavel);
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

    //--------------------------------------Após a Análise Criada--------------------------------------------------------

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
                },
                function(errResponse){
                    console.error('Error while fetching variaveis from Analise');
                }
            );
    };

    self.variavelToAnalise = function (idAnalise, variavel){
        if(variavel.idVariavel===null) {
            VariavelTAMService.addVariavelToAnalise(idAnalise, variavel)
                .then(
                    function (d) {
                        self.fetchAllVariaveisFromAnalise(idAnalise);
                        self.reset();
                        $('#criaVariavelModal').modal('hide');
                        $('body').removeClass('modal-open');
                        $('.modal-backdrop').remove();
                    },
                    function (errResponse) {
                        console.error('Error while fetching variaveis from Analise');
                    }
                )
        }else{
            VariavelTAMService.updateVariavelFromAnalise(idAnalise, variavel)
                .then(
                    function (d) {
                        self.fetchAllVariaveisFromAnalise(idAnalise);
                        self.reset();
                        $('#criaVariavelModal').modal('hide');
                        $('body').removeClass('modal-open');
                        $('.modal-backdrop').remove();
                    },
                    function (errResponse) {
                        console.error('Error while fetching variaveis from Analise');
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
                    self.fetchAllVariaveisFromAnalise(idAnalise);
                    self.reset();
                    $('#deleteVariavelModal').modal('hide');
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                },
                function(errResponse){
                    console.error('Error while fetching variaveis from Analise');
                }
            );
    };


//--------------------------------------Operações com Permissões--------------------------------------------------------

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
                },
                function(errResponse){
                    console.log("analise sem permissões")
                }
            )
    };

    self.savePermissao = function (permissao){
        PermissaoService.addPermissaoToAnalise(permissao)
            .then(function (response) {
                console.log(response);
                permissao.usuario = {idUsuario: null, nome: '', email: '', senha: ''};
                permissao.testador = false;
                permissao.administrador = false;
            })
            .catch(function(errResponse){
                console.error('Error while creating Permissao.' + errResponse);
            })
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
                        console.error('Usuario não encontrado');
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
                    self.fetchAllPermissoesFromAnalise(analise);
                    self.resetPermissao();
                },
                function(errResponse){
                    console.error('Error while updating Permissao');
                }
            )
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
                    self.fetchAllPermissoesFromAnalise(analise);
                    self.resetPermissao();
                },
                function(errResponse){
                    console.error('Error while deleting Permissao');
                }
            );
    };

});
