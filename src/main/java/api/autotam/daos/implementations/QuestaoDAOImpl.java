package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.QuestaoDAO;
import api.autotam.model.Questao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Danilo on 9/24/2016.
 */

@Repository("questaoDAO")
public class QuestaoDAOImpl extends AbstractDAO implements QuestaoDAO {
    @Override
    public void saveQuestao(Questao questao) { persist(questao);}

    @Override
    public void updateQuestao(Questao questao) { getSession().update(questao);}

    @Override
    public List<Questao> findAllQuestoesFromVariavel(int idVariavel) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM questao q WHERE p.idVariavel = :idVariavel")
                .addEntity(Questao.class)
                .setParameter("idVariavel", idVariavel);
        return (List<Questao>) query.list();
    }

    @Override
    public void deleteQuestao(int idQuestao) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM questao WHERE idQuestao = :idQuestao");
        query.setParameter("idQuestao", idQuestao);
        query.executeUpdate();
    }

    @Override
    public Questao findById(int idQuestao) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM questao q WHERE q.idQuestao = :idQuestao")
                .addEntity(Questao.class)
                .setParameter("idQuestao", idQuestao);
        return (Questao) query.uniqueResult() ;
    }


}
