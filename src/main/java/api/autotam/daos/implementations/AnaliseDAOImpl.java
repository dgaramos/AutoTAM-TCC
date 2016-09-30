package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.AnaliseDAO;
import api.autotam.model.Analise;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementação do DAO responsável pelas operações referentes a registros da classe Análise no banco de dados.
 *
 * @author Danilo
 */

@Repository("analiseDAO")
public class AnaliseDAOImpl extends AbstractDAO implements AnaliseDAO {

    /**
     * Método responsável pela operação de cadastro do registro de uma determinada Análise ao banco de dados.
     *
     * @param analise
     */
    public void saveAnalise(Analise analise) {
        persist(analise);
    }

    /**
     * Método responsável pela operação de busca do registro de uma determinada Análise no banco de dados por
     * meio de seu id.
     *
     * @param idAnalise
     * @return
     */
    public Analise findById(int idAnalise) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM analise a WHERE a.idAnalise = :idAnalise")
                .addEntity(Analise.class)
                .setParameter("idAnalise", idAnalise);
        return (Analise) query.uniqueResult() ;
    }

    /**
     * Método responsável pela operação de busca do registro de uma determinada Análise por meio de seu
     * Objeto De Análise.
     *
     * @param objetoDeAnalise
     * @return
     */
    public Analise findByObjetoDeAnalise(String objetoDeAnalise){
        Query query = getSession().createSQLQuery(
                "SELECT * FROM analise a WHERE a.objetoDeAnalise = :objetoDeAnalise")
                .addEntity(Analise.class)
                .setParameter("objetoDeAnalise", objetoDeAnalise);
        return (Analise) query.uniqueResult();
    }

    /**
     * Método responsável pela operação de atualização do registro de uma determinada Análise no banco de dados.
     *
     * @param analise
     */
    public void updateAnalise(Analise analise) {
        getSession().update(analise);
    }

    /**
     * Método responsável pela operação de remoção do registro uma determinada Análise do banco de dados por meio
     * de seu id.
     *
     * @param idAnalise
     */
    public void deleteAnalise(int idAnalise) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM analise WHERE idAnalise = :idAnalise");
        query.setParameter("idAnalise", idAnalise);
        query.executeUpdate();
    }

}
