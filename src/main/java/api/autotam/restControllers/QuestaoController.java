package api.autotam.restControllers;

import api.autotam.model.Questao;
import api.autotam.services.interfaces.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe responsável pelo mapeamento das URIs das requisições HTTP referentes a Classe Questão.
 *
 * @uri /questao
 * @author Danilo
 */

@RestController
@RequestMapping("/questao")
public class QuestaoController {

    @Autowired
    private QuestaoService questaoService;

    /**
     * Método responsável por dar a resposta a requisição HTTP/PUT referente a atualização do registro de uma
     * Variável TAM utilizando o id da Variável como parâmetro na URI
     *
     * @uri /questao/{id}
     * @param idVariavel
     * @param questao
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Questao> updateVariavel(@PathVariable("id") Integer idVariavel, @RequestBody Questao questao) {
        System.out.println("Atualizando Variável "+ questao.getEnunciado()+" da Variável TAM com id " + idVariavel);

        Questao currentQuestao = questaoService.findById(questao.getIdQuestao());

        if (currentQuestao==null) {
            System.out.println("Questão com id " + questao.getIdQuestao() + " não foi encontrada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentQuestao.setEnunciado(questao.getEnunciado());

        questaoService.updateQuestao(currentQuestao);

        return new ResponseEntity<Questao>(currentQuestao, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/GET responsável pela listagem de todas as Questões
     * vinculadas a uma determinada Variável TAM utilizando o id da Análise como parâmetro na URI
     *
     * @uri /questao/{id}
     * @param idVariavel
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Questao>> listAllQuestoesFromVariavel(@PathVariable("id") Integer idVariavel) {

        List<Questao> questoes = questaoService.findAllQuestoesFromVariavel(idVariavel);

        if(questoes.isEmpty()){
            return new ResponseEntity<List<Questao>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Questao>>(questoes, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/DELETE responsável por apagar um registro de Questão
     * por meio de seu id utilizando o id da Questão como parâmetro na URI
     *
     * @uri /questao/{id}
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Questao> deleteQuestao(@PathVariable("id") Integer id) {
        System.out.println("Buscando e apagando Questao com id " + id);

        questaoService.deleteQuestao(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
