package api.autotam.restControllers;

import api.autotam.model.Analise;
import api.autotam.service.AnaliseService;
import api.autotam.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Danilo on 9/11/2016.
 */

@RestController
@RequestMapping("/analise")
public class AnaliseController {


    @Autowired
    private UsuarioService usuarioService; //Service which will do all data retrieval/manipulation work
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

}
