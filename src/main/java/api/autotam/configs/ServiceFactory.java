package api.autotam.configs;

import api.autotam.services.implementations.AnaliseServiceImpl;
import api.autotam.services.implementations.PermissaoServiceImpl;
import api.autotam.services.implementations.UsuarioServiceImpl;
import api.autotam.services.implementations.VariavelTAMServiceImpl;
import api.autotam.services.interfaces.AnaliseService;
import api.autotam.services.interfaces.PermissaoService;
import api.autotam.services.interfaces.UsuarioService;
import api.autotam.services.interfaces.VariavelTAMService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Danilo on 9/30/2016.
 */

@Configuration
@EnableTransactionManagement
public class ServiceFactory {

    @Bean(name="usuarioService")
    public UsuarioService usuarioService(){ return new UsuarioServiceImpl();}

    @Bean (name="permissaoService")
    public PermissaoService permissaoService(){ return new PermissaoServiceImpl();}

    @Bean (name="analiseService")
    public AnaliseService analiseService(){ return new AnaliseServiceImpl();}

    @Bean (name="variavelTAMService")
    public VariavelTAMService variavelTAMService(){ return new VariavelTAMServiceImpl();}
}
