package api.autotam.service;

import api.autotam.model.Usuario;

import java.util.List;

/**
 * Essa é a interface do serviço da classe de usuario
 * Created by Danilo on 4/18/2016.
 */
public interface UsuarioService {

    void saveUsuario(Usuario usuario);

    List<Usuario> findAllUsuarios();

    void deleteUsuario(int idUsuario);

    Usuario findById(Integer id);

    Usuario findByEmail(String email);

    void updateUsuario(Usuario usuario);

    boolean isUsuarioExist(Usuario usuario);

    Usuario getUsuarioLogado();
}


