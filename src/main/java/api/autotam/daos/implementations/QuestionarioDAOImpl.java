package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.QuestionarioDAO;
import api.autotam.model.Questionario;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("questionarioDAO")
@Transactional
public class QuestionarioDAOImpl extends AbstractDAO implements QuestionarioDAO {

    @Override
    public void saveQuestionario(Questionario questionario) {
         merge(questionario);
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

    @Override
    public Questionario findByUsuarioOpcaoDeObjetoAnalise(int idUsuario, int idOpcaoDeObjeto, int idAnalise) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM questionario q " +
                        "WHERE q.idUsuario = :idUsuario " +
                        "AND q.idOpcaoDeObjeto = :idOpcaoDeObjeto " +
                        "AND q.idAnalise = :idAnalise")
                .addEntity(Questionario.class)
                .setParameter("idUsuario", idUsuario)
                .setParameter("idOpcaoDeObjeto", idOpcaoDeObjeto)
                .setParameter("idAnalise", idAnalise);

                 return (Questionario) query.uniqueResult();
    }

    @Override
    public List<Questionario> findByOpcaoDeObjetoAnalise(int idOpcaoDeObjeto, int idAnalise) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM questionario q " +
                        "WHERE q.idOpcaoDeObjeto = :idOpcaoDeObjeto " +
                        "AND q.idAnalise = :idAnalise")
                .addEntity(Questionario.class)
                .setParameter("idOpcaoDeObjeto", idOpcaoDeObjeto)
                .setParameter("idAnalise", idAnalise);

        return (List<Questionario>) query.list();
    }
}
