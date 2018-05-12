package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.QuestionarioDAO;
import api.autotam.model.Questionario;
import org.hibernate.Query;

public class QuestionarioDAOImpl extends AbstractDAO implements QuestionarioDAO {
    @Override
    public void saveQuestionario(Questionario questionario) {
        persist(questionario);
    }

    @Override
    public Questionario findById(int idQuestionario) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM questionario q WHERE q.idQuestionario = :idQuestionario")
                .addEntity(Questionario.class)
                .setParameter("idQuestionario", idQuestionario);
        return (Questionario) query.uniqueResult() ;
    }

    @Override
    public void updateQuestionario(Questionario questionario) {
        getSession().update(questionario);
    }

    @Override
    public void deleteQuestionario(int idQuestionario) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM questionario WHERE idQuestionario = :idQuestionario");
        query.setParameter("idQuestionario", idQuestionario);
        query.executeUpdate();
    }
}
