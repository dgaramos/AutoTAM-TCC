package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.UsuarioDAO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import api.autotam.model.Usuario;

import java.util.List;


/**
 * Implementação do DAO responsável pelas operações referentes a registros da classe Usuário no banco de dados.
 *
 * @author Danilo
 */

@Repository("usuarioDAO")
public class UsuarioDAOImpl extends AbstractDAO implements UsuarioDAO {

    /**
     * Método responsável pela operação de cadastro do registro de um determinado Usuário no banco de dados.
     *
     * @param usuario
     */
    public void saveUsuario(Usuario usuario) {
        persist(usuario);
    }

    /**
     * Método responsável pela operação de busca do registro de um determinado Usuário no banco de dados por
     * meio de seu id.
     *
     * @param idUsuario
     * @return
     */
    public Usuario findById(int idUsuario){
        Query query = getSession().createSQLQuery(
                "SELECT * FROM usuario u WHERE u.idUsuario = :idUsuario")
                .addEntity(Usuario.class)
                .setParameter("idUsuario", idUsuario);
        return (Usuario) query.uniqueResult() ;
    }

    /**
     * Método responsável pela operação de busca do registro de um determinado Usuário no banco de dados por
     * meio de seu email.
     *
     * @param email
     * @return
     */
    public Usuario findByEmail(String email){
        Query query = getSession().createSQLQuery(
                "SELECT * FROM usuario u WHERE u.email = :email")
                .addEntity(Usuario.class)
                .setParameter("email", email);
        return (Usuario) query.uniqueResult() ;
    }

    /**
     * Método responsável pela operação de atualização do registro de um determinado Usuário no banco de dados.
     *
     * @param usuario
     */
    public void updateUsuario(Usuario usuario){
        getSession().update(usuario);
    }

    public void deleteUsuario(int idUsuario) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM usuario WHERE idUsuario = :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        query.executeUpdate();
    }

    /**
     * Método responsável pela operação de listagem dos registros de todos os Usuários cadastrados no banco de dados.
     *
     * @return
     */
    public List<Usuario> findAllUsuarios() {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM usuario u")
                .addEntity(Usuario.class);
        return (List<Usuario>) query.list();
    }
}
