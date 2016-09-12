package api.autotam.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import api.autotam.model.Usuario;

import java.util.List;


/**
 * Implementação da interface UsuarioDAO para buscar os dados do misc no banco
 * Created by Danilo on 4/17/2016.
 */

@Repository ("usuarioDAO")
public class UsuarioDAOImpl extends AbstractDAO implements UsuarioDAO {

    public void saveUsuario(Usuario usuario) {
        persist(usuario);
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> findAllUsuarios() {
        Criteria criteria = getSession().createCriteria(Usuario.class);
        return (List<Usuario>) criteria.list();
    }

    public void deleteUsuario(Usuario usuario) {
        delete(usuario);
    }


    public Usuario findById(Integer id){
        Criteria criteria = getSession().createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("id",id));
        return (Usuario) criteria.uniqueResult();
    }

    public Usuario findByEmail(String email){
        Criteria criteria = getSession().createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("email",email));
        return (Usuario) criteria.uniqueResult();
    }


    public void updateUsuario(Usuario usuario){
        getSession().update(usuario);
    }


}
