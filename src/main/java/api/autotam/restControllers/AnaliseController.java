package api.autotam.restControllers;

import api.autotam.model.Analise;
import api.autotam.model.VariavelTAM;
import api.autotam.service.AnaliseService;
import api.autotam.service.UsuarioService;
import api.autotam.service.VariavelTAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Danilo on 9/11/2016.
 */

@RestController
@RequestMapping("/analise")
public class AnaliseController {


    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AnaliseService analiseService;
    @Autowired
    private VariavelTAMService variavelTAMService;

//-------------------Create a analise--------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createAnalise(@RequestBody Analise analise, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Analise " + analise.getNome());

        analiseService.saveAnalise(analise);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("{id}").buildAndExpand(analise.getIdAnalise()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //------------------- Delete an Analise --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Analise> deleteAnalise(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting Analise with id " + id);

        analiseService.deleteAnalise(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //------------------- Update an Analise --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Analise> updateAnalise(@PathVariable("id") Integer idAnalise, @RequestBody Analise analise) {
        System.out.println("Updating analise " + idAnalise);

        Analise currentAnalise = analiseService.findById(analise.getIdAnalise());

        if (currentAnalise==null) {
            System.out.println("Analise with id " + idAnalise + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentAnalise.setNome(analise.getNome());
        currentAnalise.setObjetoDeAnalise(analise.getObjetoDeAnalise());
        currentAnalise.setStatus(analise.getStatus());

        analiseService.updateAnalise(currentAnalise);

        return new ResponseEntity<>(currentAnalise, HttpStatus.OK);
    }
    //-------------------Add a Variavel to an Analise--------------------------------------------------------

    @RequestMapping(value = "addVariavel/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> createVariavel(@PathVariable("id") Integer idAnalise, @RequestBody VariavelTAM variavel) {
        System.out.println("Adding variavel "+ variavel.getNomeVariavel()+"to analise with id" + idAnalise);
        analiseService.addVariavelToAnalise(idAnalise, variavel);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrieve All Permissoes from Usuario--------------------------------------------------------
    @RequestMapping(value = "variaveis/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VariavelTAM>> listAllVariaveis(@PathVariable("id") Integer idAnalise) {
        List<VariavelTAM> variaveis = variavelTAMService.findAllVariaveisFromAnalise(idAnalise);
        if(variaveis.isEmpty()){
            return new ResponseEntity<List<VariavelTAM>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<VariavelTAM>>(variaveis, HttpStatus.OK);
    }

    //------------------- Delete a Variavel --------------------------------------------------------

    @RequestMapping(value = "variavel/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<VariavelTAM> deleteVariavel(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting Variavel with id " + id);

        variavelTAMService.deleteVariavel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
