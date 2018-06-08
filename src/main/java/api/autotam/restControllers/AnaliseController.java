package api.autotam.restControllers;

import api.autotam.model.Analise;
import api.autotam.model.OpcaoDeObjeto;
import api.autotam.model.VariavelTAM;
import api.autotam.services.interfaces.AnaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Classe responsável pelo mapeamento das URIs das requisições HTTP referentes a Classe Análise.
 *
 * @uri /analise
 * @author Danilo
 */

@RestController
@RequestMapping("/analise")
public class AnaliseController {

    @Autowired
    private AnaliseService analiseService;

    /**
     * Método responsável por dar a resposta a requisição HTTP/POST referente a criação de uma nova Análise TAM.
     *
     * @uri /analise
     * @param analise
     * @param ucBuilder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createAnalise(@RequestBody Analise analise, UriComponentsBuilder ucBuilder) {
        System.out.println("Criando Análise " + analise.getNome());

        analiseService.saveAnalise(analise);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("{id}").buildAndExpand(analise.getIdAnalise()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/PUT referente a atualização de uma determinada Análise
     * TAM já existente utilizando seu id como parâmetro na URI.
     *
     * @uri /analise/{id}
     * @param idAnalise
     * @param analise
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Analise> updateAnalise(@PathVariable("id") Integer idAnalise, @RequestBody Analise analise) {
        System.out.println("Atualizando Análise " + idAnalise);

        Analise currentAnalise = analiseService.findById(analise.getIdAnalise());

        if (currentAnalise==null) {
            System.out.println("Análise com id " + idAnalise + " não foi encontrada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentAnalise.setNome(analise.getNome());
        currentAnalise.setObjetoDeAnalise(analise.getObjetoDeAnalise());
        currentAnalise.setStatus(analise.getStatus());

        analiseService.updateAnalise(currentAnalise);

        return new ResponseEntity<>(currentAnalise, HttpStatus.OK);
    }


    @RequestMapping(value = "forwardStatus/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Analise> forwardAnaliseStatus(@PathVariable("id") Integer idAnalise, @RequestBody Analise analise) {
        System.out.println("Avançando status da analise " + idAnalise);

        Analise currentAnalise = analiseService.findById(analise.getIdAnalise());

        if (currentAnalise==null) {
            System.out.println("Análise com id " + idAnalise + " não foi encontrada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentAnalise.setNome(analise.getNome());
        currentAnalise.setObjetoDeAnalise(analise.getObjetoDeAnalise());
        currentAnalise.setStatus(analise.getStatus() + 1);

        analiseService.updateAnalise(currentAnalise);

        return new ResponseEntity<>(currentAnalise, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/DELETE referente a remoção de uma determinada Análise
     * TAM existente utilizando seu id como parâmetro na URI
     *
     * @uri /analise/{id}
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Analise> deleteAnalise(@PathVariable("id") Integer id) {
        System.out.println("Buscando e removendo Análise com id " + id);

        analiseService.deleteAnalise(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/POST referente a criação de uma nova Variavel TAM em
     * uma determinada Análise TAM utilizando o id da Análise como parâmetro na URI
     *
     * @uri /analise/variavel/{id}
     * @param idAnalise
     * @param variavel
     * @return
     */
    @RequestMapping(value = "variavel/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> createVariavelToAnalise(@PathVariable("id") Integer idAnalise, @RequestBody VariavelTAM variavel) {
        System.out.println("Cadastrando a  Variável "+ variavel.getNomeVariavel()+" na Análise com id " + idAnalise);

        analiseService.addVariavelToAnalise(idAnalise, variavel);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/POST referente a criação de uma nova Opção De Objeto em
     * uma determinada Análise TAM utilizando o id da Análise como parâmetro na URI
     *
     * @uri /analise/variavel/{id}
     * @param idAnalise
     * @param opcaoDeObjeto
     * @return
     */
    @RequestMapping(value = "opcaoDeObjeto/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> createOpcaoDeObjetoToAnalise(@PathVariable("id") Integer idAnalise, @RequestBody OpcaoDeObjeto opcaoDeObjeto) {
        System.out.println("Cadastrando a  Opção De Objeto "+ opcaoDeObjeto.getNome()+" na Análise com id " + idAnalise);

        opcaoDeObjeto.setAnalise(analiseService.findById(idAnalise));

        analiseService.addOpcaoDeObjetoToAnalise(idAnalise, opcaoDeObjeto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
