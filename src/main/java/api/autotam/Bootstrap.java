package api.autotam;


//hibernate test imports
//import java.util.List;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import api.autotam.config.AppConfig;
//import api.autotam.model.Usuario;
//import api.autotam.service.UsuarioService;

//REST test imports


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//this is a test class
@SpringBootApplication
public class Bootstrap {

 public static void main(String[] args) {
     SpringApplication.run(Bootstrap.class, args);
 }

/*

    //Code block to test hibernate connection
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                AppConfig.class);

        UsuarioService service = (UsuarioService) context.getBean("usuarioService");


        // Create two Usuarios


        Usuario usuario = new Usuario();
        usuario.setNome("Zé Dodjera");
        usuario.setEmail("zezinho@meupau.com");
        usuario.setSenha("cacetovisky");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Viadaum");
        usuario2.setEmail("guey@meupau.com");
        usuario2.setSenha("cacetovisky");


       //  Persist both Usuarios

        service.saveUsuario(usuario);
        service.saveUsuario(usuario2);


        // Get all usuarios list from database

        List<Usuario> usuarios = service.findAllUsuarios();
        for (Usuario user : usuarios) {
            System.out.println(user);
        }


         // delete an usuario by id

        service.deleteUsuarioByEmail("zezinho@meupau.com");


       //  Get all usuarios list from database

        List<Usuario> usuarios2 = service.findAllUsuarios();
        for (Usuario user : usuarios2) {
            System.out.println(user);
        }


        // update an employee


        Usuario usuario3 = service.findByEmail("guey@meupau.com");
        usuario3.setEmail("melhor@empresa.com");
        service.updateUsuario(usuario3);


        // Get all usuarios list from database

        List<Usuario> usuarios3 = service.findAllUsuarios();
        for (Usuario user : usuarios3) {
            System.out.println(user);
        }


        context.close();
    }*/
}