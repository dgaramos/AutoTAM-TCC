package api.autotam.dao;

import api.autotam.model.Analise;
import org.hibernate.Criteria;
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

    @SuppressWarnings("unchecked")
    public List<Analise> findAllAnalises() {
        Criteria criteria = getSession().createCriteria(Analise.class);
        return (List<Analise>) criteria.list();
    }

    public void deleteAnalise(Analise analise) {
        delete(analise);
    }

    public Analise findById(Integer id) {
        Criteria criteria = getSession().createCriteria(Analise.class);
        criteria.add(Restrictions.eq("id",id));
        return (Analise) criteria.uniqueResult();
    }

    public Analise findByObjetoDeAnalise(String objetoDeAnalise) {
        Criteria criteria = getSession().createCriteria(Analise.class);
        criteria.add(Restrictions.eq("objetoDeAnalise",objetoDeAnalise));
        return (Analise) criteria.uniqueResult();
    }

    public void updateAnalise(Analise analise) {
        getSession().update(analise);
    }
}
