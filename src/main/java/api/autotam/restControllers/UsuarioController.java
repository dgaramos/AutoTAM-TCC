package api.autotam.restControllers;


import api.autotam.services.implementations.EmailService;
import api.autotam.services.interfaces.AnaliseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import api.autotam.model.Usuario;
import api.autotam.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Classe responsável pelo mapeamento das URIs das requisições HTTP referentes a Classe Usuário.
 *
 * @uri /usuario
 * @author Danilo
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private EmailService emailService = new EmailService();

    /**
     * Método responsável por dar a resposta a requisição HTTP/GET referente a listagem de todos os Usuários.
     *
     * @uri /usuario/
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> listAllUsuarios() {
        System.out.println("Buscando Usuários");

        List<Usuario> usuarios = usuarioService.findAllUsuarios();

        if(usuarios.isEmpty()){
            return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/GET referente ao Usuário em sessão
     *
     * @uri /usuario/logged
     * @return
     */
    @RequestMapping(value = "logged", method = RequestMethod.GET)
    public ResponseEntity<Usuario> fetchLoggedUsuario() {

        Usuario loggedUser = usuarioService.getUsuarioLogado();

        if(loggedUser == null){
            System.out.println("Não há nenhum Usuário em sessão");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loggedUser, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/GET referente a busca por um Usuário por meio do
     * seu id que está sendo referenciado como parâmetro na URI
     *
     * @uri /usuario/{id}
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Integer id) {
        System.out.println("Buscando Usuário com id: " + id);

        Usuario usuario = usuarioService.findById(id);

        if (usuario == null) {
            System.out.println("Usuário com id " + id + " não foi encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/GET referente a busca por um Usuário por meio do
     * seu email que está sendo referenciado como parâmetro na URI
     *
     * @uri /usuario/{email}
     * @param email
     * @return
     */
    @RequestMapping(value = "byEmail/{email:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> getUsuarioByEmail(@PathVariable("email") String email) {
        System.out.println("Buscando Usuário com email: " + email);

        Usuario usuario = usuarioService.findByEmail(email);

        if (usuario == null) {
            System.out.println("Usuario com email " + email + "não foi encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/POST referente ao registro de um novo Usuário.
     *
     * @uri /usuario/noauth/register/
     * @param usuario
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "noauth/register/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUsuario(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
        System.out.println("Cadastrando Usuário " + usuario.getNome());

        if (usuarioService.isUsuarioExist(usuario)) {
            System.out.println("Um Usuário com Email " + usuario.getEmail() + " já está cadastrado");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        usuarioService.saveUsuario(usuario);
        emailService.registrationConfirm(usuario);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("byEmail/{email:.+}").buildAndExpand(usuario.getEmail()).toUri());
        System.out.println("passou");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/PUT referente a atualização do registro de um Usuário
     * por meio do seu id que está sendo referenciado como parâmetro na URI
     *
     * @uri /usuario/{id}
     * @param idUsuario
     * @param usuario
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Integer idUsuario, @RequestBody Usuario usuario) {
        System.out.println("Atualizando Usuário com id " + idUsuario);

        Usuario currentUsuario = usuarioService.findById(usuario.getIdUsuario());

        if (currentUsuario==null) {
            System.out.println("Usuário com id " + idUsuario + " não foi encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentUsuario.setNome(usuario.getNome());
        currentUsuario.setEmail(usuario.getEmail());
        currentUsuario.setSenha(usuario.getSenha());

        usuarioService.updateUsuario(currentUsuario);
        emailService.updateProfile(usuario);

        return new ResponseEntity<>(currentUsuario, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/DELETE responsável pela remoção do registro de um Usuário
     * por meio de seu id que está sendo referenciado como parâmetro na URI
     *
     * @uri /usuario/{id}
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable("id") Integer id) {
        System.out.println("Buscando e apagando Usuario com id " + id);

        Usuario user = usuarioService.findById(id);

        if (user == null) {
            System.out.println("Não foi possível apagar o Usuário. Usuário com id " + id + " não foi encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/GET responsável por enviar um e-mail com a senha
     * para o e-mail Cadastrado pelo Usuário que está sendo referenciado como parâmetro na URI
     *
     * @uri /usuario/noauth/password/{email}
     * @param email
     * @return
     */
    @RequestMapping(value = "noauth/password/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<Void> recoverPassword(@PathVariable("email") String email) {
        System.out.println("Enviando senha de Usuário para o email " + email);

        Usuario usuario = usuarioService.findByEmail(email);

        if (usuario == null) {
            System.out.println("Usuário com email " + email + " não foi encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        emailService.recoverPassword(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
