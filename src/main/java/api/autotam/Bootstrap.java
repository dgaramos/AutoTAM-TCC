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


@SpringBootApplication
@RestController
public class Bootstrap {

    /*Esse método retorna o usuário que está logado
      no momento por meio da URI #/userLogin
     */
    @RequestMapping("/userLogin")
    public Principal user(Principal user) {
        return user;
    }


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

                if (usuarioService.findAllUsuarios().isEmpty()) {
                    usuarioService.saveUsuario(new Usuario("admin", "admin"));
                }
            }

        };

    }

}




