package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.RespostaDAO;
import api.autotam.model.Resposta;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("respostaDAO")
public class RespostaDAOImpl extends AbstractDAO implements RespostaDAO {
    @Override
    public void saveResposta(Resposta resposta) {
        persist(resposta);
    }

    @Override
    public Resposta findById(int idResposta) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM resposta r WHERE r.idResposta = :idResposta")
                .addEntity(Resposta.class)
                .setParameter("idResposta", idResposta);
        return (Resposta) query.uniqueResult() ;
    }

    @Override
    public void updateResposta(Resposta resposta) {
        getSession().update(resposta);
    }

    @Override
    public void deleteResposta(int idResposta) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM resposta WHERE idResposta = :idResposta");
        query.setParameter("idResposta", idResposta);
        query.executeUpdate();
    }

    @Override
    public List<Resposta> findAllRespostasFromQuestao(int idQuestao) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM resposta r WHERE r.idQuestao = :idQuestao")
                .addEntity(Resposta.class)
                .setParameter("idQuestao", idQuestao);
        return (List<Resposta>) query.list();
    }

    @Override
    public List<Resposta> findAllRespostasFromQuestionario(int idQuestionario) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM resposta r WHERE r.idQuestionario = :idQuestionario")
                .addEntity(Resposta.class)
                .setParameter("idQuestionario", idQuestionario);
        return (List<Resposta>) query.list();
    }

    @Override
    public List<Resposta> findAllRespostasFromUsuario(int idUsuario) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM resposta r WHERE r.idUsuario = :idUsuario")
                .addEntity(Resposta.class)
                .setParameter("idUsuario", idUsuario);
        return (List<Resposta>) query.list();
    }
}
