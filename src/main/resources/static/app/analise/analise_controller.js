
'use strict';

controllers.controller('AnaliseController',
    ['$rootScope', '$scope', '$window', 'Global', 'AnaliseService', 'PermissaoService', 'UsuarioService', 'OpcaoDeObjetoService', 'VariavelTAMService', 'QuestaoService', 'QuestionarioService',
        function($rootScope, $scope, $window, Global, AnaliseService, PermissaoService, UsuarioService, OpcaoDeObjetoService, VariavelTAMService, QuestaoService, QuestionarioService) {

    var self = this;

            /**
             * Declarações de variáveis
             *
             */

    self.permissao = {idPermissao: null,
        usuario: {idUsuario: null, nome: '', email: '', senha: ''},
        analise: {idAnalise: null, nome: '', objetoDeAnalise: '',
            variaveis:[], opcoesDeObjeto:[], status: ''},
        testador: false, administrador: false};

    self.permissoes = [];

    self.analiseForm = {criaAnalise: false, variavelExtra: false, opcaoDeObjetoNova: false};

    self.analiseList = {variavelExtra: [], opcaoDeObjetoNova:[]};

    self.opcaoDeObjeto = {idOpcaoDeObjeto: null, nome:'', resultadosOpcaoVariaveis:[]};

    self.variavel = {idVariavel: null, nomeVariavel: '', questoes: [], variavelPadrao: false, nota: ''};

    self.questao = {idQuestao: null,  numero:'', enunciado: '', peso: '', respostas: []};

    self.respostas = [];

    self.resposta = {idResposta: null, resposta:0, usuario: {idUsuario: null, nome: '', email: '', senha: ''},
            questionario: {idQuestionario: null, tipo:'', analise: {idAnalise: null, nome: '', objetoDeAnalise: '',
            variaveis:[], opcoesDeObjeto:[], status: ''}}};

    self.questionario = {idQuestionario: null, tipo: null,
            analise: {idAnalise: null, nome: '', objetoDeAnalise: '',
            opcaoDeObjeto: {idOpcaoDeObjeto: null, nome:'', resultadosOpcaoVariaveis:[]},
            usuario: {idUsuario: null, nome: '', email: '', senha: ''}}};


            /**
             * Funções de requisição
             *
             */

    self.fetchAllAnalises = function(){
        PermissaoService.fetchAllPermissoesFromUsuario()
            .then(
                function(d) {
                    self.permissoes = d;
                    self.analiseList.variavelExtra.length = self.permissoes.length;
                    self.analiseList.opcaoDeObjetoNova.length = self.permissoes.length;
                    for(var i = 0; i < self.permissao.analise.variaveis.length; i++){
                        if(self.permissao.analise.variaveis[i].nomeVariavel === nomeVariavel) {
                            self.analiseList.variavelExtra[i] = false;
                        }
                    }
                    for(var i = 0; i < self.permissao.analise.opcoesDeObjeto.length; i++){
                        if(self.permissao.analise.opcoesDeObjeto[i].nome === nome) {
                            self.analiseList.opcaoDeObjetoNova[i] = false;
                        }
                    }

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
                    Global.fechaModal('#criaAnaliseModal');
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
                    Global.fechaModal('#criaAnaliseModal');
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
                self.reset();
                Global.fechaModal('#deleteAnaliseModal');
                self.fetchAllAnalises();
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
                variaveis:[], opcoesDeObjeto:[], status: ''},
            testador: false, administrador: false};
            self.variavel = {idVariavel: null, nomeVariavel: '',variavelPadrao: false, questoes:[], nota: ''};
            self.opcaoDeObjeto = {idOpcaoDeObjeto: null, nome:'', resultadosOpcaoVariaveis:[]};
            self.analiseForm.criaAnalise = false;
            self.analiseForm.variavelExtra = false;
            self.analiseForm.opcaoDeObjetoNova = false;
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
        //self.criaAnalise();
    };
    self.cancelEditAnalise= function(){
        self.reset();
        Global.fechaModal('#criaAnaliseModal');
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

    self.analiseListAbreVariavelExtra = function($index){
        self.variavel = {idVariavel: null, nomeVariavel: '', nota: ''};
        self.analiseList.variavelExtra[$index] = true;
    };

    self.analiseListFechaVariavelExtra = function($index){
        self.analiseList.variavelExtra[$index] = false;
        self.variavel = {idVariavel: null, nomeVariavel: '', variavelPadrao: false, nota: ''};
    };

    self.analiseFormAbreOpcaoDeObjetoNova = function(){
        self.analiseForm.opcaoDeObjetoNova = true;
    };

    self.analiseFormFechaOpcaoDeObjetoNova = function(){
        self.analiseForm.opcaoDeObjetoNova = false;
        self.opcaoDeObjeto = self.opcaoDeObjeto = {idOpcaoDeObjeto: null, nome:'', resultadosOpcaoVariaveis:[]};
    };

    self.analiseListRemoveOpcaoDeObjeto = function(nome){
        console.log('Opcao de Objeto a ser removida: ' + nome);
        for(var i = 0; i < self.permissao.analise.opcoesDeObjeto.length; i++){
            if(self.permissao.analise.opcoesDeObjeto[i].nome === nome) {
                self.permissao.analise.opcoesDeObjeto.splice(i, 1);
                break;
            }
        }
    };

    self.analiseFormAdicionaOpcaoDeObjetoNova = function(){
        self.permissao.analise.opcoesDeObjeto.push(self.opcaoDeObjeto);
        self.analiseForm.opcaoDeObjetoNova = false;
        self.opcaoDeObjeto = self.opcaoDeObjeto = {idOpcaoDeObjeto: null, nome:'', resultadosOpcaoVariaveis:[]};
    };

    self.analiseListAbreOpcaoDeObjetoNova = function($index){
        self.analiseList.opcaoDeObjetoNova[$index] = true;
    };

    self.analiseListFechaOpcaoDeObjetoNova = function($index) {
        self.analiseList.opcaoDeObjetoNova[$index] = false;
        self.opcaoDeObjeto = {idOpcaoDeObjeto: null, nome:'', resultadosOpcaoVariaveis:[]};
    };
            /**
             * Funções de manipulação de Variável TAM
             *
             */

    self.fetchAllVariaveisFromAnalise = function(idAnalise, $index){
        VariavelTAMService.fetchAllVariaveisFromAnalise(idAnalise)
            .then(
                function(d) {
                    console.log(d);
                    self.permissoes[$index].analise.variaveis = d;
                })
            .catch(
                function(errResponse){
                    console.error('Erro ao encontrar Variáveis da Análise' + errResponse);
                }
            );
    };

    self.variavelToAnalise = function (idAnalise, variavel, $index){
        if(variavel.idVariavel===null) {
            VariavelTAMService.addVariavelToAnalise(idAnalise, variavel)
                .then(
                    function (d) {
                        console.log(d);
                        self.fetchAllVariaveisFromAnalise(idAnalise, $index);
                        self.reset();
                        Global.fechaModal('#gerenciaVariavelModal');
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
                        QuestaoService.fetchAllQuestoesFromVariavel(variavel.idVariavel)
                            .then(
                                function(q){
                                    variavel.questoes = q;
                                },
                                function(errResponse){
                                    console.error('Erro ao carregar Questões ' + errResponse);
                                }
                            );
                        self.fetchAllVariaveisFromAnalise(idAnalise, $index);
                        self.reset();
                        Global.fechaModal('#gerenciaVariavelModal');
                    },
                    function (errResponse) {
                        console.error('Erro ao atualizar Variável ' + errResponse);
                    }
                )
        }
    };

    self.selectVariavel = function (variavel, analise){
        self.variavel = angular.copy(variavel);
        self.fetchAllQuestoesFromVariavel(variavel.idVariavel);
        self.permissao.analise = angular.copy(analise);

    };

    self.deleteVariavel = function (idVariavel, idAnalise){
        VariavelTAMService.deleteVariavel(idVariavel)
            .then(
                function(d){
                    console.log(d);
                    self.fetchAllAnalises();
                    self.reset();
                    Global.fechaModal('#deleteVariavelModal');
                },
                function(errResponse){
                    console.error('Erro ao deletar Variável ' + errResponse);
                }
            );
    };

    self.cancelDeleteVariavel = function(){
        self.reset();
        Global.fechaModal('#deleteVariavelModal');
    };
            /**
             * Funções de gerenciamento das questões da variável TAM
             *
             */
    self.adicionaQuestaoToVariavel = function(){
        self.questao.numero = self.variavel.questoes.length + 1;
        self.variavel.questoes.push(self.questao);
        self.questao = {idQuestao: null, numero:'', enunciado: '', peso: '', resposta: ''};
    };

    self.selectQuestao = function (questao){
        self.questao = angular.copy(questao);
    };

     self.fetchAllQuestoesFromVariavel = function(idVariavel){
         QuestaoService.fetchAllQuestoesFromVariavel(idVariavel)
             .then(
                 function(d) {
                     self.variavel.questoes = d;
                 })
             .catch(
                 function(errResponse){
                     console.error('Erro ao encontrar Questões da Variável' + errResponse);
                 }
             );
    };

    self.deleteQuestao = function (idQuestao, idVariavel){
        QuestaoService.deleteQuestao(idQuestao)
            .then(
                function(d){
                    console.log(d);
                    self.questao = {idQuestao: null, numero:'', enunciado: '', peso: '', resposta: ''};
                    self.fetchAllQuestoesFromVariavel(idVariavel);
                },
                function(errResponse){
                    console.error('Erro ao deletar Questão ' + errResponse);
                }
            );
    };

            /**
             * Funções de gerenciamento de Opção de Objeto
             *
             */

    self.fetchAllOpcoesDeObjetoFromAnalise = function(idAnalise, $index){
        OpcaoDeObjetoService.fetchAllOpcoesDeObjetoFromAnalise(idAnalise)
            .then(
                function(d) {
                    self.permissoes[$index].analise.opcoesDeObjeto = d;
                    })
            .catch(
                function(errResponse) {
                    console.error('Erro ao encontrar Opções de Objeto da Análise' + errResponse);
                });
        };

    self.opcaoDeObjetoToAnalise = function (idAnalise, opcaoDeObjeto, $index){
        if(opcaoDeObjeto.idOpcaoDeObjeto===null) {
            OpcaoDeObjetoService.addOpcaoDeObjetoToAnalise(idAnalise, opcaoDeObjeto)
                .then(
                    function (d) {
                        console.log(d);
                        self.fetchAllOpcoesDeObjetoFromAnalise(idAnalise, $index);
                        self.reset();
                        self.analiseListFechaOpcaoDeObjetoNova()
                        },
                    function (errResponse) {
                        console.error('Erro ao adicionar Opções de Objeto a Análise:' + errResponse);
                        })
        }else{
            OpcaoDeObjetoService.updateOpcaoDeObjetoFromAnalise(opcaoDeObjeto.idOpcaoDeObjeto, opcaoDeObjeto)
                .then(
                    function (d) {
                        console.log(d);
                        self.fetchAllOpcoesDeObjetoFromAnalise(idAnalise, $index);
                        self.reset();
                        self.analiseListFechaOpcaoDeObjetoNova()
                        },
                    function (errResponse) {
                        console.error('Erro ao atualizar Opcao De Objeto ' + errResponse);
                    })
        }
    };

    self.selectOpcaoDeObjeto = function (opcaoDeObjeto, analise){
        self.opcaoDeObjeto = angular.copy(opcaoDeObjeto);
        self.permissao.analise = angular.copy(analise);
    };

    self.editOpcaoDeObjeto = function (opcaoDeObjeto, analise,$index) {
        self.selectOpcaoDeObjeto(opcaoDeObjeto, analise);
        self.analiseListAbreOpcaoDeObjetoNova($index);
    };

    self.deleteOpcaoDeObjeto = function (idOpcaoDeObjeto, idAnalise){
        OpcaoDeObjetoService.deleteOpcaoDeObjeto(idOpcaoDeObjeto)
            .then(
                function(d){
                    console.log(d);
                    self.fetchAllAnalises();
                    self.reset();
                    Global.fechaModal('#deleteOpcaoDeObjetoModal');
                    },
                function(errResponse){
                    console.error('Erro ao deletar Opcao de Objeto ' + errResponse);
                    }
                    );
        };
    self.cancelDeleteOpcaoDeObjeto = function(){
        self.reset();
        Global.fechaModal('#deleteOpcaoDeObjetoModal');
    };
            /**
             * Funções de gerenciamento de questionario
             *
             */



    self.resetQuestionario = function() {
        self.respostas = [];
        self.resposta = {
            idResposta: null, resposta: null, usuario: {idUsuario: null, nome: '', email: '', senha: ''},
            questionario: {
                idQuestionario: null, tipo: null,
                opcaoDeObjeto: {idOpcaoDeObjeto: null, nome:'', resultadosOpcaoVariaveis:[]},
                analise: {idAnalise: null, nome: '', objetoDeAnalise: '',
                    variaveis: [], opcoesDeObjeto: [], status: ''}}};

        self.questionario = {
            idQuestionario: null, tipo: null,
                opcaoDeObjeto: {idOpcaoDeObjeto: null, nome:'', resultadosOpcaoVariaveis:[]},
            analise: {idAnalise: null, nome: '', objetoDeAnalise: '',
                variaveis: [], opcoesDeObjeto: [], status: ''}};
    };


    self.initializeQuestionario = function(opcaoDeObjeto, analise){
        self.resetQuestionario();
        self.selectOpcaoDeObjeto(opcaoDeObjeto, analise);
        for (var i = 0; i < self.permissao.analise.variaveis.length; i++) {
            self.respostas.push([]);
            for (var j = 0; j < self.permissao.analise.variaveis[i].questoes.length; j++) {
                self.respostas[i].push({idResposta: null, resposta:0, usuario: $rootScope.loggedUsuario,
                    questionario: self.questionario});
            }
        }
    };
    self.atribuirRespostasAQuestoes = function(analise, respostas){
        for (var i = 0; i < analise.variaveis.length; i++) {
            for (var j = 0; j < analise.variaveis[i].questoes.length; j++) {
                console.log(analise.variaveis[i]);
                console.log(analise.variaveis[i].questoes[j]);
                respostas[i][j].questao = analise.variaveis[i].questoes[j];
                analise.variaveis[i].questoes[j].respostas.push(
                    respostas[i][j].resposta);
            }
        }

    };
    self.saveQuestionario = function(analise,opcaoDeObjeto, respostas){
        self.atribuirRespostasAQuestoes(analise, respostas);
        console.log(analise);
        QuestionarioService.saveQuestionario(opcaoDeObjeto.idOpcaoDeObjeto, analise)
            .then(function(response){
                console.log(response);
                self.resetQuestionario();
                Global.fechaModal('#responderQuestionarioModal');
        })
            .catch(
                function(errResponse){
                    console.error('Error while creating Questionario.' + errResponse);
                }
            );
    };

    self.questionarioJaRespondido = function(idAnalise, opcaoDeObjeto){
        QuestionarioService.questionarioJaRespondido(idAnalise, opcaoDeObjeto.idOpcaoDeObjeto)
            .then(function(response){
                opcaoDeObjeto.questionarioJaRespondido = response;
                console.log(response);
            })
            .catch(
                function(errResponse){
                    console.error('Error' + errResponse);
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
    };

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
                    console.error('Error while creating Permissao.' + errResponse);
                }
            );
    };

    self.adicionaPermissao = function(analise){
        self.erroPermissao = false;
        if(self.permissaoConvite.usuario.email === $rootScope.loggedUsuario.email){
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
                                        console.log(p);
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
    };

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
