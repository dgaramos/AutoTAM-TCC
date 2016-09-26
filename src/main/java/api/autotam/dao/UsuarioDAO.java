package api.autotam.dao;

import api.autotam.model.Usuario;

import java.util.List;

/**
 * Interface para criação de DAO de misc
 * Created by Danilo on 4/17/2016.
 */
public interface UsuarioDAO {

    void saveUsuario(Usuario usuario);

    void updateUsuario(Usuario usuario);

    List<Usuario> findAllUsuarios();

    void deleteUsuario(int idUsuario);

    Usuario findById(int idUsuario);

    Usuario findByEmail(String email);

}