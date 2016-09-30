package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.PermissaoDAO;
import api.autotam.model.Permissao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Danilo on 9/11/2016.
 */

@Repository("permissaoDAO")
public class PermissaoDAOImpl extends AbstractDAO implements PermissaoDAO {

    public void savePermissao(Permissao permissao) {
        saveOrUpdate(permissao);
    }

    public Permissao findById(int idPermissao) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM permissao p WHERE p.idPermissao = :idPermissao")
                .addEntity(Permissao.class)
                .setParameter("idPermissao", idPermissao);
        return (Permissao) query.uniqueResult() ;

    }

    public void updatePermissao(Permissao permissao) {
        getSession().update(permissao);
    }

    public void deletePermissao(int idPermissao){
        Query query = getSession().createSQLQuery(
                "DELETE FROM permissao WHERE idPermissao = :idPermissao");
        query.setParameter("idPermissao", idPermissao);
        query.executeUpdate();

    }

    @Override
    public boolean usuarioHasPermissaoToAnalise(int idAnalise, int idUsuario) {
        Query query = getSession().createSQLQuery(
                "SELECT u.idUsuario FROM permissao p JOIN usuario u USING (idUsuario) WHERE idAnalise =: idAnalise")
                .addEntity(Integer.class)
                .setParameter("idAnalise", idAnalise);
        return query.list().contains(idUsuario);
    }

    @Override
    public boolean usuarioIsAdministrador(int idAnalise, int idUsuario) {
        Query query = getSession().createSQLQuery(
                "SELECT p.Administrador FROM permissao p WHERE idAnalise =: idAnalise AND idUsuario =: idUsuario")
                .addEntity(boolean.class)
                .setParameter("idAnalise", idAnalise)
                .setParameter("idUsuario", idUsuario);

        return (boolean) query.uniqueResult();
    }

    public List<Permissao> findAllPermissoesFromUsuario(int idUsuario) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM permissao p WHERE p.idUsuario = :idUsuario")
                .addEntity(Permissao.class)
                .setParameter("idUsuario", idUsuario);
        return (List<Permissao>) query.list();
    }

    public List<Permissao> findAllPermissoesFromAnalise(int idAnalise) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM permissao p WHERE p.idAnalise = :idAnalise")
                .addEntity(Permissao.class)
                .setParameter("idAnalise", idAnalise);
        return (List<Permissao>) query.list();
    }

}
