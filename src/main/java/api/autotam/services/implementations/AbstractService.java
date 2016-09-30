package api.autotam.services.implementations;

import api.autotam.daos.interfaces.UsuarioDAO;
import api.autotam.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Danilo on 9/17/2016.
 */
public abstract class AbstractService {

    @Autowired
    private UsuarioDAO usuarioDAO;


    public Usuario getUsuarioLogado(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Usuario loggedUser = usuarioDAO.findByEmail(currentUserName);
            return loggedUser;
        }

        return null;
    }
}
