package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.VariavelTAMDAO;
import api.autotam.model.VariavelTAM;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementação do DAO responsável pelas operações referentes a registros da classe Variável TAM no banco de dados.
 *
 * @author Danilo
 */

@Repository("variavelTAMDAO")
public class VariavelTAMDAOImpl extends AbstractDAO implements VariavelTAMDAO {

    /**
     * Método responsável pela operação de cadastro do registro de uma determinada Variável TAM no banco de dados.
     *
     * @param variavel
     */
    @Override
    public void saveVariavel(VariavelTAM variavel) {persist(variavel);}

    /**
     * Método responsável pela operação de busca do registro de uma determinada Variável TAM no banco de dados por
     * meio do seu id.
     *
     * @param idVariavel
     * @return
     */
    @Override
    public VariavelTAM findById(int idVariavel) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM  variavelTAM v WHERE v.idVariavel = :idVariavel")
                .addEntity(VariavelTAM.class)
                .setParameter("idVariavel", idVariavel);
        return (VariavelTAM) query.uniqueResult() ;
    }

    /**
     * Método responsável pela operação de atualização do registro de uma determinada Variável TAM no banco de dados.
     *
     * @param variavel
     */
    @Override
    public void updateVariavel(VariavelTAM variavel) { getSession().update(variavel);}

    /**
     * Método responsável pela operação de remoção do registro uma determinada Variável TAM do banco de dados por meio do seu id.
     *
     * @param idVariavel
     */
    @Override
    public void deleteVariavel(int idVariavel) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM variavelTAM WHERE idVariavel = :idVariavel");
        query.setParameter("idVariavel", idVariavel);
        query.executeUpdate();
    }

    /**
     * Método responsável pela operação de listagem dos registros todas as Variáveis TAM vinculadas a uma determinada
     * Análise por meio de seu id.
     *
     * @param idAnalise
     * @return
     */
    @Override
    public List<VariavelTAM> findAllVariaveisFromAnalise(int idAnalise) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM  variavelTAM v WHERE v.idAnalise = :idAnalise")
                .addEntity(VariavelTAM.class)
                .setParameter("idAnalise", idAnalise);
        return (List<VariavelTAM>) query.list();
    }

}
