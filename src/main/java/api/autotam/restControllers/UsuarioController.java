package api.autotam.restControllers;


import api.autotam.model.Permissao;
import api.autotam.service.EmailService;
import api.autotam.service.PermissaoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import api.autotam.model.Usuario;
import api.autotam.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Essa classe trata de buscar os dados dos usuarios para que eles sejam entregues como JSON
 * nas requisições
 *
 * Created by Danilo on 9/4/2016.
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service; //Service which will do all data retrieval/manipulation work
    @Autowired
    private PermissaoService permissaoService;

    private EmailService emailService = new EmailService();


   //-------------------Retrieve All Usuarios--------------------------------------------------------
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> listAllUsuarios() {
        List<Usuario> usuarios = service.findAllUsuarios();
        if(usuarios.isEmpty()){
            return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    //-------------------Retrieve Single Usuario--------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> getUserById(@PathVariable("id") Integer id) {
        System.out.println("Fetching User with id: " + id);
        Usuario usuario = service.findById(id);
        if (usuario == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    //-------------------Retrieve Single Usuario via Email   --------------------------------------------------------

    @RequestMapping(value = "byEmail/{email:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> getUserByEmail(@PathVariable("email") String email) {
        System.out.println("Fetching User with email: " + email);
        Usuario usuario = service.findByEmail(email);
        if (usuario == null) {
            System.out.println("User with username " + email + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    //-------------------Create a Usuario--------------------------------------------------------

    @RequestMapping(value = "noauth/register/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + usuario.getNome());

        if (service.isUsuarioExist(usuario)) {
            System.out.println("A User with id " + usuario.getIdUsuario() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (service.findByEmail(usuario.getEmail()) != null) {
            System.out.println("A User with username " + usuario.getEmail() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        service.saveUsuario(usuario);
        emailService.registrationConfirm(usuario);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("byEmail/{email:.+}").buildAndExpand(usuario.getEmail()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Usuario> updateUser(@PathVariable("id") Integer idUsuario, @RequestBody Usuario usuario) {
        System.out.println("Updating User " + idUsuario);

        Usuario currentUsuario = service.findById(usuario.getIdUsuario());

        if (currentUsuario==null) {
            System.out.println("User with id " + idUsuario + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentUsuario.setNome(usuario.getNome());
        currentUsuario.setEmail(usuario.getEmail());
        currentUsuario.setSenha(usuario.getSenha());

        service.updateUsuario(currentUsuario);
        emailService.updateProfile(usuario);
        return new ResponseEntity<>(currentUsuario, HttpStatus.OK);
    }

//------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Usuario> deleteUser(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting User with id " + id);

        Usuario user = service.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.deleteUsuario(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//-----------------------Recover Password -----------------------

    @RequestMapping(value = "noauth/password/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<Void> recoverPassword(@PathVariable("email") String email) {
        System.out.println("Fetching User with email: " + email);
        Usuario usuario = service.findByEmail(email);
        if (usuario == null) {
            System.out.println("User with username " + email + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        emailService.recoverPassword(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrieve All Permissoes from Usuario--------------------------------------------------------
    @RequestMapping(value = "permissoes/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Permissao>> listAllPermissoes() {
        List<Permissao> permissoes = permissaoService.findAllPermissoes();
        if(permissoes.isEmpty()){
            return new ResponseEntity<List<Permissao>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Permissao>>(permissoes, HttpStatus.OK);
    }

}
