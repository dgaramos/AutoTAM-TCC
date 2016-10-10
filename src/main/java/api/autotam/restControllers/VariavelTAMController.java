package api.autotam.restControllers;

import api.autotam.model.Questao;
import api.autotam.model.VariavelTAM;
import api.autotam.services.interfaces.VariavelTAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Danilo
 */

@RestController
@RequestMapping("/variavelTAM")
public class VariavelTAMController {

    @Autowired
    private VariavelTAMService variavelTAMService;

    /**
     * Método responsável por dar a resposta a requisição HTTP/PUT referente a atualização do registro de uma
     * Variável TAM utilizando o id da Variável como parâmetro na URI
     *
     * @uri /variavelTAM/{id}
     * @param idVariavel
     * @param variavel
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<VariavelTAM> updateVariavel(@PathVariable("id") Integer idVariavel, @RequestBody VariavelTAM variavel) {
        System.out.println("Atualizando Variável "+ variavel.getNomeVariavel());

        VariavelTAM currentVariavel = variavelTAMService.findById(idVariavel);

        if (currentVariavel == null) {
            System.out.println("Variavel com id " + variavel.getIdVariavel() + " não foi encontrada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentVariavel.setNomeVariavel(variavel.getNomeVariavel());
        currentVariavel.setQuestoes(variavel.getQuestoes());

        variavelTAMService.updateVariavel(currentVariavel);

        return new ResponseEntity<VariavelTAM>(currentVariavel, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/GET responsável pela listagem de todas as Variaveis
     * vinculadas a uma determinada Análise utilizando o id da Análise como parâmetro na URI
     *
     * @uri /variavelTAM/{id}
     * @param idAnalise
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VariavelTAM>> listAllVariaveisFromAnalise(@PathVariable("id") Integer idAnalise) {

        List<VariavelTAM> variaveis = variavelTAMService.findAllVariaveisFromAnalise(idAnalise);

        if(variaveis.isEmpty()){
            return new ResponseEntity<List<VariavelTAM>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<VariavelTAM>>(variaveis, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/DELETE responsável por apagar um registro de Variável TAM
     * por meio de seu id utilizando o id da Variável como parâmetro na URI
     *
     * @uri /variavelTAM/{id}
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<VariavelTAM> deleteVariavel(@PathVariable("id") Integer id) {
        System.out.println("Buscando e apagando Variavel com id " + id);

        variavelTAMService.deleteVariavel(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/POST referente a criação de uma nova Questão em
     * uma determinada Variável TAM utilizando o id da Variável como parâmetro na URI
     *
     * @uri /variavelTAM/questao/{id}
     * @param idVariavel
     * @param questao
     * @return
     */
    @RequestMapping(value = "questao/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> createQuestaoToVariavel(@PathVariable("id") Integer idVariavel, @RequestBody Questao questao) {
        System.out.println("Cadastrando a  Questão "+ questao.getEnunciado()+" na Variável TAM com id " + idVariavel);

        variavelTAMService.addQuestaoToVariavel(idVariavel, questao);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
