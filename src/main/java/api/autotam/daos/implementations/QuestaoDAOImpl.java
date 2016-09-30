package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.QuestaoDAO;
import api.autotam.model.Questao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementação do DAO responsável pelas operações referentes a registros da classe Questão no banco de dados.
 *
 * @author Danilo
 */

@Repository("questaoDAO")
public class QuestaoDAOImpl extends AbstractDAO implements QuestaoDAO {

    /**
     * Método responsável pela operação de cadastro de uma determinada Questão no banco de dados.
     *
     * @param questao
     */
    @Override
    public void saveQuestao(Questao questao) { persist(questao);}

    /**
     * Método responsável pela operação de busca do registro de uma determinada Questão no banco de dados por meio de seu id.
     *
     * @param idQuestao
     * @return
     */
    @Override
    public Questao findById(int idQuestao) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM questao q WHERE q.idQuestao = :idQuestao")
                .addEntity(Questao.class)
                .setParameter("idQuestao", idQuestao);
        return (Questao) query.uniqueResult() ;
    }

    /**
     * Método responsável pela operação de atualização do registro de uma determinada Questão no banco de dados.
     *
     * @param questao
     */
    @Override
    public void updateQuestao(Questao questao) { getSession().update(questao);}

    /**
     * Método responsável pela operação de remoção do registro de uma determinada Questão do banco de dados por
     * meio de seu id.
     *
     * @param idQuestao
     */
    @Override
    public void deleteQuestao(int idQuestao) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM questao WHERE idQuestao = :idQuestao");
        query.setParameter("idQuestao", idQuestao);
        query.executeUpdate();
    }

    /**
     * Método responsável pela operação de listagem dos registros de todas as Questões vinculadas a uma determinada
     * Variável TAM por meio de seu id.
     *
     * @param idVariavel
     * @return
     */
    @Override
    public List<Questao> findAllQuestoesFromVariavel(int idVariavel) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM questao q WHERE p.idVariavel = :idVariavel")
                .addEntity(Questao.class)
                .setParameter("idVariavel", idVariavel);
        return (List<Questao>) query.list();
    }

}
