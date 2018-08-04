package api.autotam;


import api.autotam.model.Usuario;
import api.autotam.services.interfaces.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Classe responsável por inicializar a aplicação.
 *
 * @author Danilo
 */
@SpringBootApplication
@RestController
public class Bootstrap {

    /**
     * Método responsável por passar o token de sessão.
     *
     * @param user
     * @uri /userlogin
     * @return
     */
    @RequestMapping("/userLogin")
    public Principal user(Principal user) { return user; }


    public static void main(String[] args) {

        SpringApplication.run(Bootstrap.class, args);
    }

    @Bean
    CommandLineRunner init(final UsuarioService usuarioService) {

        return new CommandLineRunner() {


            @Override
            public void run(String... arg0) throws Exception {
                /*Esse bloco de código checa se existem usuários cadastrados no banco de dados
                  quando o programa é iniciado, e caso não exista, ele gera um usuário
                  com login admin e senha admin.
                */

                List<Usuario> listaUsuarios = usuarioService.findAllUsuarios();
                if (listaUsuarios.isEmpty()) {
                    usuarioService.saveUsuario(new Usuario("autotamtcc@gmail.com", "autotam", "admin"));
                }else{
                    for (Usuario usuario : listaUsuarios) {
                        usuarioService.updateUsuario(usuario);
                    }
                }
            }

        };

    }

}




