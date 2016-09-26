package api.autotam.dao;

import api.autotam.model.Permissao;
import api.autotam.model.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Danilo on 9/11/2016.
 */

@Repository("permissaoDAO")
public class PermissaoDAOImpl extends AbstractDAO implements PermissaoDAO{


    public void savePermissao(Permissao permissao) {
        persist(permissao);
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


    public List<Permissao> findAllPermissoesByUsuario(int idUsuario) {
        Query query = getSession().createSQLQuery(
                "select * from permissao p where p.idUsuario = :idUsuario")
                .addEntity(Permissao.class)
                .setParameter("idUsuario", idUsuario);
        return (List<Permissao>) query.list();
    }

    public void updatePermissao(Permissao permissao) {
        getSession().update(permissao);
    }
}
