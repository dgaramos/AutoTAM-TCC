package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.VariavelTAMDAO;
import api.autotam.model.VariavelTAM;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Danilo on 9/20/2016.
 */

@Repository("variavelTAMDAO")
public class VariavelTAMDAOImpl extends AbstractDAO implements VariavelTAMDAO {

    @Override
    public void saveVariavel(VariavelTAM variavel) {persist(variavel);}

    @Override
    public void updateVariavel(VariavelTAM variavel) { getSession().update(variavel);}

    @Override
    public List<VariavelTAM> findAllVariaveisFromAnalise(int idAnalise) {
        Query query = getSession().createSQLQuery(
                "select * from variavelTAM v where v.idAnalise = :idAnalise")
                .addEntity(VariavelTAM.class)
                .setParameter("idAnalise", idAnalise);
        return (List<VariavelTAM>) query.list();
    }

    @Override
    public void deleteVariavel(int idVariavel) {
        Query query = getSession().createSQLQuery(
                "delete from variavelTAM where idVariavel = :idVariavel");
        query.setParameter("idVariavel", idVariavel);
        query.executeUpdate();
    }

    @Override
    public VariavelTAM findById(int idVariavel) {
        Query query = getSession().createSQLQuery(
                "select * from variavelTAM v where v.idVariavel = :idVariavel")
                .addEntity(VariavelTAM.class)
                .setParameter("idVariavel", idVariavel);
        return (VariavelTAM) query.uniqueResult() ;
    }

}
