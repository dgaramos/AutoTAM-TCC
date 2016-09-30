package api.autotam.daos.interfaces;

import api.autotam.model.Usuario;

import java.util.List;

/**
 * Interface para criação de DAO de misc
 * Created by Danilo on 4/17/2016.
 */
public interface UsuarioDAO {

    void saveUsuario(Usuario usuario);

    Usuario findById(int idUsuario);

    Usuario findByEmail(String email);

    void updateUsuario(Usuario usuario);

    void deleteUsuario(int idUsuario);

    List<Usuario> findAllUsuarios();

}