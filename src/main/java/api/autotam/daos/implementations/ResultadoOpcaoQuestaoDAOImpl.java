package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.ResultadoOpcaoQuestaoDAO;
import api.autotam.model.ResultadoOpcaoQuestao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("resultadoOpcaoQuestaoDAO")
public class ResultadoOpcaoQuestaoDAOImpl extends AbstractDAO implements ResultadoOpcaoQuestaoDAO {

    @Override
    public void saveResultadoOpcaoQuestao(ResultadoOpcaoQuestao resultadoOpcaoQuestao) {
        saveOrUpdate(resultadoOpcaoQuestao);
    }

    @Override
    public ResultadoOpcaoQuestao findById(int idResultadoOpcaoQuestao) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM resultadoOpcaoQuestao roq " +
                           "WHERE roq.idResultadoOpcaoQuestao = :idResultadoOpcaoQuestao")
                .addEntity(ResultadoOpcaoQuestao.class)
                .setParameter("idResultadoOpcaoQuestao", idResultadoOpcaoQuestao);
        return (ResultadoOpcaoQuestao) query.uniqueResult() ;
    }

    @Override
    public void updateResultadoOpcaoQuestao(ResultadoOpcaoQuestao resultadoOpcaoQuestao) {
        getSession().update(resultadoOpcaoQuestao);
    }

    @Override
    public void deleteResultadoOpcaoQuestao(int idResultadoOpcaoQuestao) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM resultadoOpcaoQuestao " +
                           "WHERE idResultadoOpcaoQuestao = :idResultadoOpcaoQuestao");
        query.setParameter("idResultadoOpcaoQuestao", idResultadoOpcaoQuestao);
        query.executeUpdate();
    }

    @Override
    public List<ResultadoOpcaoQuestao> findAllResultadosOpcaoQuestaoFromResultadoOpcaoVariavel(int idResultadoOpcaoVariavel) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM resultadoOpcaoQuestao roq " +
                           "WHERE roq.idResultadoOpcaoVariavel = :idResultadoOpcaoVariavel")
                .addEntity(ResultadoOpcaoQuestao.class)
                .setParameter("idResultadoOpcaoVariavel", idResultadoOpcaoVariavel);
        return (List<ResultadoOpcaoQuestao>) query.list();
    }

    @Override
    public ResultadoOpcaoQuestao findFromOpcaoQuestao(int idOpcaoDeObjeto, int idQuestao) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM resultadoOpcaoQuestao roq WHERE " +
                           "roq.idOpcaoDeObjeto = :idOpcaoDeObjeto AND " +
                           "roq.idQuestao = :idQuestao")
                .addEntity(ResultadoOpcaoQuestao.class)
                .setParameter("idOpcaoDeObjeto", idOpcaoDeObjeto)
                .setParameter("idQuestao", idQuestao);
        return (ResultadoOpcaoQuestao) query.uniqueResult() ;
    }
}
