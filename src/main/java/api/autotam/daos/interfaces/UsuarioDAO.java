package api.autotam.daos.interfaces;

import api.autotam.model.Usuario;

import java.util.List;

/**
 * Interface responsável por encapsular as assinaturas dos métodos das operações referentes aos registros no banco de
 * dados da classe Usuário.
 *
 * @author Danilo
 */

public interface UsuarioDAO {

    void saveUsuario(Usuario usuario);

    Usuario findById(int idUsuario);

    Usuario findByEmail(String email);

    void updateUsuario(Usuario usuario);

    void deleteUsuario(int idUsuario);

    List<Usuario> findAllUsuarios();

}