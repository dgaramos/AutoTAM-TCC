package api.autotam.services.interfaces;

import api.autotam.model.Usuario;

import java.util.List;

/**
 * Essa é a interface do serviço da classe de usuario
 * Created by Danilo on 4/18/2016.
 */
public interface UsuarioService {

    void saveUsuario(Usuario usuario);

    Usuario findById(Integer id);

    Usuario findByEmail(String email);

    Usuario getUsuarioLogado();

    void updateUsuario(Usuario usuario);

    void deleteUsuario(int idUsuario);

    boolean isUsuarioExist(Usuario usuario);

    List<Usuario> findAllUsuarios();

}


