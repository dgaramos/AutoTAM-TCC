package src.autotam.dao;

import src.autotam.model.Usuario;

import java.util.List;

/**
 * Created by Danilo on 4/17/2016.
 */
public interface UsuarioDAO {

    void saveUsuario(Usuario usuario);

    List<Usuario> findAllUsuarios();

    void deleteUsuarioByEmail(String email);

    Usuario findByEmail(String email);

    void updateUsuario(Usuario usuario);
}