package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.PermissaoDAO;
import api.autotam.model.Permissao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementação do DAO responsável pelas operações referentes a registros da classe Permissão no banco de dados
 *
 * @author Danilo
 */

@Repository("permissaoDAO")
public class PermissaoDAOImpl extends AbstractDAO implements PermissaoDAO {

    /**
     * Método responsável pela operação de cadastro do registro de uma determinada Permissão ao banco de dados.
     *
     * @param permissao
     */
    public void savePermissao(Permissao permissao) {
        persist(permissao);
    }

    /**
     * Método responsável pela operação de busca do registro de uma determinada Permissão por meio de seu id.
     *
     * @param idPermissao
     * @return
     */
    public Permissao findById(int idPermissao) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM permissao p WHERE p.idPermissao = :idPermissao")
                .addEntity(Permissao.class)
                .setParameter("idPermissao", idPermissao);
        return (Permissao) query.uniqueResult() ;
    }

    /**
     * Método responsável pela operação de atualização do registro de uma determinada Permissão no banco de dados.
     *
     * @param permissao
     */
    public void updatePermissao(Permissao permissao) {
        getSession().update(permissao);
    }

    /**
     * Método responsável pela operação de remoção do registro de uma determinada Permissão do banco de dados por
     * meio do seu id.
     *
     * @param idPermissao
     */
    public void deletePermissao(int idPermissao){
        Query query = getSession().createSQLQuery(
                "DELETE FROM permissao WHERE idPermissao = :idPermissao");
        query.setParameter("idPermissao", idPermissao);
        query.executeUpdate();
    }

    /**
     * Método responsável pela operação de verifição de se o Usuário já possui alguma permissão em relação a uma
     * determinada Análise por meio de seu id.
     *
     * @param idAnalise
     * @param idUsuario
     * @return
     */
    @Override
    public boolean usuarioHasPermissaoToAnalise(int idAnalise, int idUsuario) {
        Query query = getSession().createSQLQuery(
                "SELECT u.idUsuario FROM permissao p JOIN usuario u USING (idUsuario) WHERE idAnalise = :idAnalise")
                .setParameter("idAnalise", idAnalise);
        return query.list().contains(idUsuario);
    }

    /**
     * Método responsável pela operação de verificação de se o Usuário em questão possui permissão de Administrador
     * de uma determinada Análise por meio de seu id.
     *
     * @param idAnalise
     * @param idUsuario
     * @return
     */
    @Override
    public boolean usuarioIsAdministrador(int idAnalise, int idUsuario) {
        Query query = getSession().createSQLQuery(
                "SELECT p.isAdministrador FROM permissao p WHERE idAnalise = :idAnalise AND idUsuario = :idUsuario")
                .setParameter("idAnalise", idAnalise)
                .setParameter("idUsuario", idUsuario);
        return (boolean) query.uniqueResult();
    }

    /**
     * Método responsável pela operação de verificação de se o Usuário em questão possui permissão de Testador
     * de uma determinada Análise por meio de seu id.
     *
     * @param idAnalise
     * @param idUsuario
     * @return
     */
    @Override
    public boolean usuarioIsTestador(int idAnalise, int idUsuario) {
        Query query = getSession().createSQLQuery(
                "SELECT p.isTestador FROM permissao p WHERE idAnalise = :idAnalise AND idUsuario = :idUsuario")
                .setParameter("idAnalise", idAnalise)
                .setParameter("idUsuario", idUsuario);
        return (boolean) query.uniqueResult();
    }

    /**
     * Método responsável pela operação de listagem dos registros de todas as Permissões vinculadas a um determinado
     * Usuário por meio de seu id.
     *
     * @param idUsuario
     * @return
     */
    public List<Permissao> findAllPermissoesFromUsuario(int idUsuario) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM permissao p WHERE p.idUsuario = :idUsuario")
                .addEntity(Permissao.class)
                .setParameter("idUsuario", idUsuario);
        return (List<Permissao>) query.list();
    }

    /**
     * Método responsável pela operação de listagem dos registros de todas as Permissões vinculadas a uma determinada
     * Análise por meio de seu id.
     *
     * @param idAnalise
     * @return
     */
    public List<Permissao> findAllPermissoesFromAnalise(int idAnalise) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM permissao p WHERE p.idAnalise = :idAnalise")
                .addEntity(Permissao.class)
                .setParameter("idAnalise", idAnalise);
        return (List<Permissao>) query.list();
    }

}
