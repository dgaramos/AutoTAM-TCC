package api.autotam.daos.interfaces;

import api.autotam.model.Permissao;

import java.util.List;

/**
 * Created by Danilo on 9/11/2016.
 */
public interface PermissaoDAO {

    void savePermissao(Permissao permissao);

    List<Permissao> findAllPermissoesFromUsuario(int idUsuario);

    List<Permissao> findAllPermissoesFromAnalise (int idAnalise);

    boolean usuarioHasPermissaoToAnalise(int idAnalise, int idUsuario);

    void deletePermissao(int idPermissao);

    Permissao findById(int idPermissao);

    void updatePermissao(Permissao permissao);
}
