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
        analise: {idAnalise: null, nome: '', objetoDeAnalise: '', variaveis:[], status: ''},
        testador: false, administrador: false};
    self.permissoes = [];
    self.analiseForm = false;
    self.variavel = {idVariavel: null, nomeVariavel: '', nota: ''};

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
            self.resetAnalise();
        }else{
            self.updateAnalise(self.permissao.analise, self.permissao.analise.idAnalise);
            console.log('Analise updated with nome ', self.permissao.analise.nome);
            self.fetchAllAnalises();
            self.resetAnalise();
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
            self.resetAnalise();
        }
        self.deleteAnalise(idAnalise);
    };

    self.resetAnalise = function(){
        self.permissao = {idPermissao: null,
            usuario: {idUsuario: null, nome: '', email: '', senha: ''},
            analise: {idAnalise: null, nome: '', objetoDeAnalise: '', status: ''},
            testador: false, administrador: false};
            self.analiseForm = false;
    };

    self.criaAnalise = function(){
        self.analiseForm = true;
        $window.scrollTo(0, 0);
    };
//--------------------------------------Operações com Variáveis--------------------------------------------------------
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
            }
        }
    };

    self.resetVariavel = function(){
        self.variavel = {idVariavel: null, nomeVariavel: '', nota: ''};
    };

})