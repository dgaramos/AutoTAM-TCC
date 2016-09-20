package api.autotam.restControllers;

import api.autotam.model.Analise;
import api.autotam.service.AnaliseService;
import api.autotam.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

//-------------------Create a analise--------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createAnalise(@RequestBody Analise analise, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Analise " + analise.getNome());

        if (analiseService.isAnaliseExist(analise)) {
            System.out.println("A analise with id " + analise.getIdAnalise() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        analiseService.saveAnalise(analise);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("{id}").buildAndExpand(analise.getIdAnalise()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //------------------- Delete an Analise --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Analise> deleteAnalise(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting User with id " + id);

        Analise analise = analiseService.findById(id);
        if (analise == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        analiseService.deleteAnalise(analise);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //------------------- Update a User --------------------------------------------------------

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


}
