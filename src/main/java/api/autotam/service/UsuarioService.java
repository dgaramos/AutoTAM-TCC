package api.autotam.service;

import api.autotam.model.Usuario;

import java.util.List;

/**
 * Created by Danilo on 4/18/2016.
 */
public interface UsuarioService {

    void saveUsuario(Usuario usuario);

    List<Usuario> findAllUsuarios();

    void deleteUsuarioByEmail(String email);

    Usuario findByEmail(String email);

    void updateUsuario(Usuario usuario);

    public boolean isUsuarioExist(Usuario usuario);
}


