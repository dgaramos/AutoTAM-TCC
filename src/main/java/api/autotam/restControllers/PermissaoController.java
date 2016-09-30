package api.autotam.restControllers;

import api.autotam.model.Permissao;
import api.autotam.services.interfaces.PermissaoService;
import api.autotam.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Danilo on 9/29/2016.
 */

@RestController
@RequestMapping("/permissao")
public class PermissaoController {


    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PermissaoService permissaoService;

    //-------------------Retrieve All Permissões from Usuario--------------------------------------------------------
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Permissao>> listAllPermissoesFromUsuario() {
        List<Permissao> permissoes = permissaoService.findAllPermissoesFromUsuario(usuarioService.getUsuarioLogado().getIdUsuario());
        if(permissoes.isEmpty()){
            return new ResponseEntity<List<Permissao>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Permissao>>(permissoes, HttpStatus.OK);
    }


    //------------------- Retrieve Permissões from Analise --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Permissao>> listAllPermissoes(@PathVariable("id") Integer idAnalise) {
        List<Permissao> permissoes = permissaoService.findAllPermissoesFromAnalise(idAnalise);
        if(permissoes.isEmpty()){
            return new ResponseEntity<List<Permissao>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Permissao>>(permissoes, HttpStatus.OK);
    }

    //-------------------Add a Permissao to an Analise--------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createPermissao(@RequestBody Permissao permissao) {
        System.out.println("Adding Permissao to Analise "+ permissao.getAnalise().getNome()+ " to Usuario with Nome " + permissao.getUsuario().getEmail());
        permissaoService.savePermissao(permissao);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------------- Update a Permissao --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Permissao> updatePermissao(@PathVariable("id") Integer idPermissao, @RequestBody Permissao permissao) {
        System.out.println("Updating Permissao " + idPermissao);

        Permissao currentPermissao = permissaoService.findById(idPermissao);

        if (currentPermissao==null) {
            System.out.println("Permissao with id " + idPermissao + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentPermissao.setTestador(permissao.isTestador());
        currentPermissao.setAdministrador(permissao.isAdministrador());

        permissaoService.updatePermissao(currentPermissao);

        return new ResponseEntity<>(currentPermissao, HttpStatus.OK);
    }

    //------------------- Delete a Permissao --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Permissao> deletePermissao(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting Permissao with id " + id);

        permissaoService.deletePermissao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
