/**
 * Created by Danilo on 9/17/2016.
 */
'use strict';

controllers.controller('AnaliseController', function($scope, AnaliseService, $window) {
    var self = this;
    self.usuario = {idUsuario: null, nome: '', email: '', senha: ''};
    self.usuarios = [];
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
        AnaliseService.fetchAllAnalises()
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

    self.deleteAnalise = function(idAnalise){
        AnaliseService.deleteAnalise(idAnalise)
            .then(
                self.fetchAllAnalises,
                function(errResponse){
                    console.error('Error while deleting Analise.');
                }
            );
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

    self.editAnalise = function(idAnalise){
        console.log('Analise to be edited', idAnalise);
        self.criaAnalise();
        for(var i = 0; i < self.permissoes.length; i++){
            if(self.permissoes[i].analise.idAnalise === idAnalise) {
                self.permissao.analise = angular.copy(self.permissoes[i].analise);
                break;
            }
        }
    };

    self.removeAnalise = function(idAnalise){
        console.log('Analise id to be deleted', idAnalise);
        if(self.permissao.analise.idAnalise === idAnalise) {//clean form if the user to be deleted is shown there.
            self.reset();
        }
        self.deleteAnalise(idAnalise);
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

    self.editVariavel = function(idAnalise,idVariavel){
        console.log('Variavel to be edited', idVariavel);
        for(var i = 0; i < self.permissoes.length; i++){
            if(self.permissoes[i].analise.idAnalise === idAnalise) {
                self.permissao.analise = angular.copy(self.permissoes[i].analise);
                for(var i = 0; i < self.permissao.analise.variaveis.length; i++){
                    if(self.permissao.analise.variaveis[i].idVariavel === idVariavel) {
                        self.variavel = angular.copy(self.permissao.analise.variaveis[i]);
                        break;
                    }
                }
                break;
            }
        }
    };

    self.addVariaveltoAnalise = function (idAnalise, variavel){
        AnaliseService.addVariaveltoAnalise(idAnalise, variavel);
        $('.modal-backdrop').remove();
        self.fetchAllAnalises();
        self.reset();
    };
})