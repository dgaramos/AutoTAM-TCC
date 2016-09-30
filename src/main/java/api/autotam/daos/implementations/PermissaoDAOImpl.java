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

    public void deletePermissao(int idPermissao){
    Query query = getSession().createSQLQuery(
            "delete from permissao where idPermissao = :idPermissao");
        query.setParameter("idPermissao", idPermissao);
        query.executeUpdate();

    }

    public Permissao findById(int idPermissao) {
        Query query = getSession().createSQLQuery(
                "select * from permissao p where p.idPermissao = :idPermissao")
                .addEntity(Permissao.class)
                .setParameter("idPermissao", idPermissao);
        return (Permissao) query.uniqueResult() ;

    }


    public List<Permissao> findAllPermissoesFromUsuario(int idUsuario) {
        Query query = getSession().createSQLQuery(
                "select * from permissao p where p.idUsuario = :idUsuario")
                .addEntity(Permissao.class)
                .setParameter("idUsuario", idUsuario);
        return (List<Permissao>) query.list();
    }

    public List<Permissao> findAllPermissoesFromAnalise(int idAnalise) {
        Query query = getSession().createSQLQuery(
                "select * from permissao p where p.idAnalise = :idAnalise")
                .addEntity(Permissao.class)
                .setParameter("idAnalise", idAnalise);
        return (List<Permissao>) query.list();
    }

    public void updatePermissao(Permissao permissao) {
        getSession().update(permissao);
    }
}
