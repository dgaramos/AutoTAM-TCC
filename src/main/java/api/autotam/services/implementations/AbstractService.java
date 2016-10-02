package api.autotam.services.implementations;

import api.autotam.daos.interfaces.PermissaoDAO;
import api.autotam.daos.interfaces.UsuarioDAO;
import api.autotam.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Classe de serviço Abstrata responsável por encapsular as operações básicas referêntes as regras de negócio
 * da aplicação para que possam ser usadas em outras classes
 *
 * @author Danilo
 */

public abstract class AbstractService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private PermissaoDAO permissaoDAO;

    /**
     * Método responsável por retornar o Usuário em sessão.
     *
     * @return
     */
    public Usuario getUsuarioLogado(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Usuario loggedUser = usuarioDAO.findByEmail(currentUserName);
            return loggedUser;
        }

        return null;
    }

    /**
     * Método responsável por verificar se o Usuário em sessão tem Permissão
     * para uma determinada Análise por meio de seu id.
     *
     * @param idAnalise
     * @return
     */
    public boolean usuarioLogadoHasPermissão(int idAnalise){
        return permissaoDAO.usuarioHasPermissaoToAnalise(idAnalise, getUsuarioLogado().getIdUsuario());
    }

    /**
     * Método responsável por verificar se o Usuário em sessão tem Permissão de administrador
     * para uma determinada Análise por meio de seu id.
     *
     * @param idAnalise
     * @return
     */
    public boolean usuarioLogadoIsAdministrador(int idAnalise){
        return permissaoDAO.usuarioIsAdministrador(idAnalise, getUsuarioLogado().getIdUsuario());
    }
}
