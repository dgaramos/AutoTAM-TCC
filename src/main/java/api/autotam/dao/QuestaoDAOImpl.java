package api.autotam.dao;

import api.autotam.model.Questao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Danilo on 9/24/2016.
 */

@Repository("questaoDAO")
public class QuestaoDAOImpl extends AbstractDAO implements QuestaoDAO{
    @Override
    public void saveQuestao(Questao questao) { persist(questao);}

    @Override
    public List<Questao> findAllQuestoesFromVariavel(int idVariavel) {
        Query query = getSession().createSQLQuery(
                "select * from questao q where p.idVariavel = :idVariavel")
                .addEntity(Questao.class)
                .setParameter("idVariavel", idVariavel);
        return (List<Questao>) query.list();
    }

    @Override
    public void deleteQuestao(Questao questao) {delete(questao);}

    @Override
    public Questao findById(Integer id) {
        Criteria criteria = getSession().createCriteria(Questao.class);
        criteria.add(Restrictions.eq("id",id));
        return (Questao) criteria.uniqueResult();
    }

    @Override
    public void updateQuestao(Questao questao) { getSession().update(questao);}
}
