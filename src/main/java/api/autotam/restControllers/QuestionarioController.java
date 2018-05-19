package api.autotam.restControllers;


import api.autotam.model.Questao;
import api.autotam.model.Questionario;
import api.autotam.model.VariavelTAM;
import api.autotam.services.interfaces.QuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/questionario")
public class QuestionarioController {

    @Autowired
    private QuestionarioService questionarioService;

    /**
     * Método responsável por dar a resposta a requisição HTTP/POST referente a criação de novas Permissões vinculadas a
     * uma determinada Análise
     *
     * @uri /questionario
     * @param questionario
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createQuestionario(@RequestBody Questionario questionario, UriComponentsBuilder ucBuilder) {

        questionario.getOpcaoDeObjeto().setAnalise(questionario.getAnalise());

        for (VariavelTAM variavel : questionario.getAnalise().getVariaveis()){
            for(Questao questao : variavel.getQuestoes()){
                questao.getRespostas().get(questao.getRespostas().size()-1).setQuestionario(questionario);
            }
        }

        System.out.println("Criando Questionario vinculado a Analise "+ questionario.getAnalise().getNome() +
                " de objeto "+ questionario.getOpcaoDeObjeto().getNome() +
                " testando " + questionario.getOpcaoDeObjeto().getAnalise().getNome() +
                " para o Usuario com email " + questionario.getUsuario().getEmail() );

        questionarioService.saveQuestionario(questionario);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("{id}").buildAndExpand(questionario.getIdQuestionario()).toUri());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/PUT referente a atualização de uma determinada Permissão
     * em relação ao seu tipo (Observador, Administrador, Testador) utilizando seu id como parametro na URI
     *
     * @uri /questionario/{id}
     * @param idQuestionario
     * @param questionario
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Questionario> updateQuestionario(@PathVariable("id") Integer idQuestionario, @RequestBody Questionario questionario) {
        System.out.println("Atualizando Questionario " + idQuestionario );

        Questionario currentQuestionario = questionarioService.findById(idQuestionario);

        if (currentQuestionario==null) {
            System.out.println("Questionario com id " + idQuestionario + " não foi encontrada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        questionarioService.updateQuestionario(currentQuestionario);

        return new ResponseEntity<>(currentQuestionario, HttpStatus.OK);
    }

    /**
     *
     * @uri /
     * @param idUsuario
     * @param idAnalise
     * @param idOpcaoDeObjeto
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> usuarioJaRespondeuOpcaoDeObjeto(int idUsuario, int idAnalise, int idOpcaoDeObjeto) {
        boolean status = questionarioService.usuarioJaRespondeuOpcaoDeObjeto(idUsuario, idAnalise, idOpcaoDeObjeto);

        return new ResponseEntity<Boolean>(status, HttpStatus.OK);
    }
}
