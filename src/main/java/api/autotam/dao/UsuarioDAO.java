package api.autotam.dao;

import api.autotam.model.Usuario;

import java.util.List;

/**
 * Created by Danilo on 4/17/2016.
 */
public interface UsuarioDAO {

    void saveUsuario(Usuario usuario);

    List<Usuario> findAllUsuarios();

    void deleteUsuarioByEmail(Usuario usuario);

    Usuario findById(Integer id);

    Usuario findByEmail(String Email);

    void updateUsuario(Usuario usuario);
}