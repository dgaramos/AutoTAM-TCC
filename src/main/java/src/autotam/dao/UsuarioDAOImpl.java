package src.autotam.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import src.autotam.model.Usuario;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


/**
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

    public void deleteUsuarioByEmail(String email) {
        Query query = getSession().createSQLQuery("delete from Usuario where email = :email");
        query.setString("email", email);
        query.executeUpdate();
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
