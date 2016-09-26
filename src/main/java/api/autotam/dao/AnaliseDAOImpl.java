package api.autotam.dao;

import api.autotam.model.Analise;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Danilo on 9/11/2016.
 */
@Repository("analiseDAO")
public class AnaliseDAOImpl extends AbstractDAO implements AnaliseDAO {

    public void saveAnalise(Analise analise) {
        persist(analise);
    }

    public void updateAnalise(Analise analise) {
        getSession().update(analise);
    }

    public void deleteAnalise(int idAnalise) {
        Query query = getSession().createSQLQuery(
                "delete from analise where idAnalise = :idAnalise");
        query.setParameter("idAnalise", idAnalise);
        query.executeUpdate();
    }

    public Analise findById(int idAnalise) {
        Query query = getSession().createSQLQuery(
                "select * from analise a where a.idAnalise = :idAnalise")
                .addEntity(Analise.class)
                .setParameter("idAnalise", idAnalise);
        return (Analise) query.uniqueResult() ;
    }

    public Analise findByObjetoDeAnalise(String objetoDeAnalise){
        Query query = getSession().createSQLQuery(
                "select * from analise a where a.objetoDeAnalise = :objetoDeAnalise")
                .addEntity(Analise.class)
                .setParameter("objetoDeAnalise", objetoDeAnalise);
        return (Analise) query.uniqueResult();
    }
}
