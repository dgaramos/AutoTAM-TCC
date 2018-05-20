package api.autotam.restControllers;


import api.autotam.model.*;
import api.autotam.services.interfaces.AnaliseService;
import api.autotam.services.interfaces.OpcaoDeObjetoService;
import api.autotam.services.interfaces.QuestionarioService;
import api.autotam.services.interfaces.UsuarioService;
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

    @Autowired
    private AnaliseService analiseService;

    @Autowired
    private OpcaoDeObjetoService opcaoDeObjetoService;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Método responsável por dar a resposta a requisição HTTP/POST referente a criação de novos Questionarios vinculadas a
     * uma determinada Análise
     *
     * @uri /questionario/{idOpcaoDeObjeto}
     * @param idOpcaoDeObjeto
     * @param analise
     * @return
     */
    @RequestMapping(value = "/{idOpcaoDeObjeto}",method = RequestMethod.POST)
    public ResponseEntity<Void> createQuestionario(@PathVariable("idOpcaoDeObjeto") Integer idOpcaoDeObjeto, @RequestBody Analise analise) {


        OpcaoDeObjeto opcaoDeObjeto = opcaoDeObjetoService.findById(idOpcaoDeObjeto);

        Questionario questionario = new Questionario();
        questionario.setAnalise(analise);
        questionario.setOpcaoDeObjeto(opcaoDeObjeto);
        questionario.setUsuario(usuarioService.getUsuarioLogado());

        System.out.println(questionario.getOpcaoDeObjeto().getNome());
        System.out.println(questionario.getOpcaoDeObjeto().getAnalise().getNome());

        System.out.println(analise.getVariaveis().size());

        for (VariavelTAM variavel : analise.getVariaveis()){
            for(Questao questao : variavel.getQuestoes()){
                System.out.println(questao.getRespostas().size());
                questao.getRespostas().get(questao.getRespostas().size()-1).setQuestionario(questionario);
            }
        }

        System.out.println("Criando Questionario vinculado a Analise "+ questionario.getAnalise().getNome() +
                " de objeto "+ questionario.getOpcaoDeObjeto().getNome() +
                " testando " + questionario.getOpcaoDeObjeto().getAnalise().getNome() +
                " para o Usuario com email " + questionario.getUsuario().getEmail() );

        questionarioService.saveQuestionario(questionario);


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
     * @uri /questionario/{idAnalise}{idOpcaoDeObjeto}
     * @param idAnalise
     * @param idOpcaoDeObjeto
     * @return
     */
    @RequestMapping(value = "/{idAnalise}/{idOpcaoDeObjeto}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> usuarioJaRespondeuOpcaoDeObjeto(@PathVariable("idAnalise") int idAnalise, @PathVariable("idOpcaoDeObjeto") int idOpcaoDeObjeto) {
        boolean status = questionarioService.usuarioJaRespondeuOpcaoDeObjeto(usuarioService.getUsuarioLogado().getIdUsuario(), idAnalise, idOpcaoDeObjeto);

        System.out.println(status);
        return new ResponseEntity<Boolean>(status, HttpStatus.OK);
    }
}
