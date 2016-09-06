package api.autotam;


import api.autotam.model.Usuario;
import api.autotam.service.UsuarioService;
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
                if (usuarioService.findAllUsuarios().isEmpty()) {
                    usuarioService.saveUsuario(new Usuario("admin", "admin"));
                }
            }

        };

    }

}




