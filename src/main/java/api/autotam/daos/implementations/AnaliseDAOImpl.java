package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.AnaliseDAO;
import api.autotam.model.Analise;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Danilo on 9/11/2016.
 */
@Repository("analiseDAO")
public class AnaliseDAOImpl extends AbstractDAO implements AnaliseDAO {

    public void saveAnalise(Analise analise) {
        persist(analise);
    }

    public Analise findById(int idAnalise) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM analise a WHERE a.idAnalise = :idAnalise")
                .addEntity(Analise.class)
                .setParameter("idAnalise", idAnalise);
        return (Analise) query.uniqueResult() ;
    }

    public Analise findByObjetoDeAnalise(String objetoDeAnalise){
        Query query = getSession().createSQLQuery(
                "SELECT * FROM analise a WHERE a.objetoDeAnalise = :objetoDeAnalise")
                .addEntity(Analise.class)
                .setParameter("objetoDeAnalise", objetoDeAnalise);
        return (Analise) query.uniqueResult();
    }

    public void updateAnalise(Analise analise) {
        getSession().update(analise);
    }

    public void deleteAnalise(int idAnalise) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM analise WHERE idAnalise = :idAnalise");
        query.setParameter("idAnalise", idAnalise);
        query.executeUpdate();
    }

}
