package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.UsuarioDAO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import api.autotam.model.Usuario;

import java.util.List;


/**
 * Implementação da interface UsuarioDAO para buscar os dados do misc no banco
 * Created by Danilo on 4/17/2016.
 */

@Repository("usuarioDAO")
public class UsuarioDAOImpl extends AbstractDAO implements UsuarioDAO {

    public void saveUsuario(Usuario usuario) {
        persist(usuario);
    }

    public void updateUsuario(Usuario usuario){
        getSession().update(usuario);
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> findAllUsuarios() {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM usuario u")
                .addEntity(Usuario.class);
        return (List<Usuario>) query.list();
    }

    public void deleteUsuario(int idUsuario) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM usuario WHERE idUsuario = :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        query.executeUpdate();
    }


    public Usuario findById(int idUsuario){
        Query query = getSession().createSQLQuery(
                "SELECT * FROM usuario u WHERE u.idUsuario = :idUsuario")
                .addEntity(Usuario.class)
                .setParameter("idUsuario", idUsuario);
        return (Usuario) query.uniqueResult() ;
    }

    public Usuario findByEmail(String email){
        Query query = getSession().createSQLQuery(
                "SELECT * FROM usuario u WHERE u.email = :email")
                .addEntity(Usuario.class)
                .setParameter("email", email);
        return (Usuario) query.uniqueResult() ;
    }

}
