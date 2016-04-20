package src.autotam.restControllers;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import src.autotam.model.Usuario;
import src.autotam.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

/*import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import src.autotam.model.Usuario;
*/
import java.util.List;


@RestController
public class UsuarioController {


/*
   @RequestMapping( value = "/teste/", method = RequestMethod.GET)
    public Usuario teste(@RequestParam(value = "id", defaultValue="1")Integer id,
                         @RequestParam(value="nome", defaultValue="João") String nome,
                         @RequestParam(value="email", defaultValue="joao@joao.com") String email,
                         @RequestParam(value="senha", defaultValue="1l0v3p3n15") String senha) {
        Usuario u = new Usuario();

        u.setIdUsuario(id);
        u.setNome(nome);
        u.setEmail(email);
        u.setSenha(senha);

        return u;
    }*/


     @Autowired
    private UsuarioService service; //Service which will do all data retrieval/manipulation work


   //-------------------Retrieve All Usuarios--------------------------------------------------------
    @RequestMapping(value = "/usuario/", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> listAllUsuarios() {
        List<Usuario> usuarios = service.findAllUsuarios();
        if(usuarios.isEmpty()){
            return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    //-------------------Retrieve Single Usuario--------------------------------------------------------

    @RequestMapping(value = "/usuario/{email:}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> getUser(@PathVariable("email") String email) {
        System.out.println("Fetching User with email " + email);
        Usuario usuario = service.findByEmail(email);
        if (usuario == null) {
            System.out.println("User with email " + email + " not found");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    //-------------------Create a Usuario--------------------------------------------------------

    @RequestMapping(value = "/usuario/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + usuario.getNome());

        if (service.isUsuarioExist(usuario)) {
            System.out.println("A User with e-mail " + usuario.getEmail() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.saveUsuario(usuario);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(usuario.getIdUsuario()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/usuario/{email:}", method = RequestMethod.PUT)
    public ResponseEntity<Usuario> updateUser(@PathVariable("email") String email, @RequestBody Usuario usuario) {
        System.out.println("Updating User " + email);

        Usuario currentUsuario = service.findByEmail(email);

        if (currentUsuario==null) {
            System.out.println("User with email " + email + " not found");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }

        currentUsuario.setNome(usuario.getNome());
        currentUsuario.setEmail(usuario.getEmail());
        currentUsuario.setSenha(usuario.getSenha());

        service.updateUsuario(currentUsuario);
        return new ResponseEntity<Usuario>(currentUsuario, HttpStatus.OK);
    }

//------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/usuario/{email}", method = RequestMethod.DELETE)
    public ResponseEntity<Usuario> deleteUser(@PathVariable("email") String email) {
        System.out.println("Fetching & Deleting User with email " + email);

        Usuario user = service.findByEmail(email);
        if (user == null) {
            System.out.println("Unable to delete. User with email " + email + " not found");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }

        service.deleteUsuarioByEmail("email");
        return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
    }

}
