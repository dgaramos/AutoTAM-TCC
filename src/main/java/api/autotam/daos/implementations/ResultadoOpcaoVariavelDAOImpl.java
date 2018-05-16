package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.ResultadoOpcaoVariavelDAO;
import api.autotam.model.ResultadoOpcaoVariavel;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("resultadoOpcaoVariavelDAO")
public class ResultadoOpcaoVariavelDAOImpl extends AbstractDAO implements ResultadoOpcaoVariavelDAO {
    @Override
    public void saveResultadoOpcaoVariavel(ResultadoOpcaoVariavel resultadoOpcaoVariavel) {
        persist(resultadoOpcaoVariavel);
    }

    @Override
    public ResultadoOpcaoVariavel findById(int idResultadoOpcaoVariavel) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM resultadoOpcaoVariavel rov WHERE rov.idResultadoOpcaoVariavel = :idResultadoOpcaoVariavel")
                .addEntity(ResultadoOpcaoVariavel.class)
                .setParameter("idResultadoOpcaoVariavel", idResultadoOpcaoVariavel);
        return (ResultadoOpcaoVariavel) query.uniqueResult() ;
    }

    @Override
    public void updateResultadoOpcaoVariavel(ResultadoOpcaoVariavel resultadoOpcaoVariavel) {
        getSession().update(resultadoOpcaoVariavel);
    }

    @Override
    public void deleteResultadoOpcaoVariavel(int idResultadoOpcaoVariavel) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM resultadoOpcaoVariavel WHERE idResultadoOpcaoVariavel = :idResultadoOpcaoVariavel");
        query.setParameter("idResultadoOpcaoVariavel", idResultadoOpcaoVariavel);
        query.executeUpdate();
    }
}
