package api.autotam.restControllers;

import api.autotam.model.OpcaoDeObjeto;
import api.autotam.services.interfaces.OpcaoDeObjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opcaoDeObjeto")
public class OpcaoDeObjetoController {


    @Autowired
    private OpcaoDeObjetoService opcaoDeObjetoService;

    /**
     * Método responsável por dar a resposta a requisição HTTP/PUT referente a atualização do registro de uma
     * Opção De Objeto  utilizando o id da Opção De Objeto como parâmetro na URI
     *
     * @uri /opcaoDeObjeto/{id}
     * @param idOpcaoDeObjeto
     * @param opcaoDeObjeto
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OpcaoDeObjeto> updateOpcaoDeObjeto(@PathVariable("id") Integer idOpcaoDeObjeto, @RequestBody OpcaoDeObjeto opcaoDeObjeto) {
        System.out.println("Atualizando Opção De Objeto "+ opcaoDeObjeto.getNome());

        OpcaoDeObjeto currentOpcaoDeObjeto = opcaoDeObjetoService.findById(idOpcaoDeObjeto);

        System.out.println(currentOpcaoDeObjeto.getNome() + currentOpcaoDeObjeto.getIdOpcaoDeObjeto());

        if (currentOpcaoDeObjeto == null) {
            System.out.println("OpcaoDeObjeto com id " + opcaoDeObjeto.getIdOpcaoDeObjeto() + " não foi encontrada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentOpcaoDeObjeto.setNome(opcaoDeObjeto.getNome());

        opcaoDeObjetoService.updateOpcaoDeObjeto(currentOpcaoDeObjeto);

        return new ResponseEntity<OpcaoDeObjeto>(currentOpcaoDeObjeto, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/GET responsável pela listagem de todas as Variaveis
     * vinculadas a uma determinada Análise utilizando o id da Análise como parâmetro na URI
     *
     * @uri /opcaoDeObjeto/{id}
     * @param idAnalise
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OpcaoDeObjeto>> listAllVariaveisFromAnalise(@PathVariable("id") Integer idAnalise) {

        List<OpcaoDeObjeto> variaveis = opcaoDeObjetoService.findAllOpcoesDeObjetoFromAnalise(idAnalise);

        if(variaveis.isEmpty()){
            return new ResponseEntity<List<OpcaoDeObjeto>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<OpcaoDeObjeto>>(variaveis, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/DELETE responsável por apagar um registro de Opção De Objeto 
     * por meio de seu id utilizando o id da Opção De Objeto como parâmetro na URI
     *
     * @uri /opcaoDeObjeto/{id}
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<OpcaoDeObjeto> deleteOpcaoDeObjeto(@PathVariable("id") Integer id) {
        System.out.println("Buscando e apagando OpcaoDeObjeto com id " + id);

        opcaoDeObjetoService.deleteOpcaoDeObjeto(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
