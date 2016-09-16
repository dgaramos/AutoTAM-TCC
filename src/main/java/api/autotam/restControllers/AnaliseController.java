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

//-------------------Create a Usuario--------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createAnalise(@RequestBody Analise analise, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Analise " + analise.getNome());

        /*if (analiseService.isUsuarioExist(usuario)) {
            System.out.println("A User with id " + usuario.getIdUsuario() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (analiseService.findByEmail(usuario.getEmail()) != null) {
            System.out.println("A User with username " + usuario.getEmail() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        analiseService.saveUsuario(usuario);
        emailService.registrationConfirm(usuario);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("byEmail/{email:.+}").buildAndExpand(usuario.getEmail()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);*/
        return null;
    }

}
