package api.autotam.daos.interfaces;

import api.autotam.model.Permissao;

import java.util.List;

/**
 * Created by Danilo on 9/11/2016.
 */
public interface PermissaoDAO {

    void savePermissao(Permissao permissao);

    Permissao findById(int idPermissao);

    void updatePermissao(Permissao permissao);

    void deletePermissao(int idPermissao);

    boolean usuarioHasPermissaoToAnalise(int idAnalise, int idUsuario);

    boolean usuarioIsAdministrador(int idAnalise, int idUsuario);

    List<Permissao> findAllPermissoesFromUsuario(int idUsuario);

    List<Permissao> findAllPermissoesFromAnalise (int idAnalise);
}
