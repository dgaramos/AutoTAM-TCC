package api.autotam.configs.security;

import api.autotam.model.Usuario;
import api.autotam.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * Classe de configuração responsável pelo atrelamento de objetos classe Usuário que vem carregada com os
 * dados dos usuários vindos do banco de dados com a classe User, permitindo assim que as credenciais de acesso
 * (email e senha) de cada Usuário guardado no banco de dados possam ser utilizadas para login.
 *
 * @author Danilo
 */

@Configuration
public class WebSecurityAuth extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Usuario usuario = usuarioService.findByEmail(email);
                if (usuario != null) {
                    return new User(usuario.getEmail(), usuario.getSenha(), true, true, true, true,
                            AuthorityUtils.createAuthorityList("USER"));
                } else {
                    throw new UsernameNotFoundException("could not find the misc '"
                            + email + "'");
                }
            }

        };
    }
}