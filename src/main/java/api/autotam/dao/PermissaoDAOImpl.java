package api.autotam.dao;

import api.autotam.model.Permissao;
import api.autotam.model.Usuario;
import org.hibernate.Criteria;
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

    @SuppressWarnings("unchecked")
    public List<Permissao> findAllPermissoes() {
        Criteria criteria = getSession().createCriteria(Permissao.class);
        return (List<Permissao>) criteria.list();
    }

    public void deletePermissao(Permissao permissao) {
        delete(permissao);
    }

    public Permissao findById(Integer id) {
        Criteria criteria = getSession().createCriteria(Permissao.class);
        criteria.add(Restrictions.eq("id",id));
        return (Permissao) criteria.uniqueResult();
    }


    public List<Permissao> findAllPermissoesByUsuario(Usuario usuario) {
        Criteria criteria = getSession().createCriteria(Permissao.class);
        criteria.add(Restrictions.eq("usuario",usuario));
        return (List<Permissao>) criteria.list();
    }

    public void updatePermissao(Permissao permissao) {
        getSession().update(permissao);
    }
}
