package api.autotam.dao;

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
    public void saveVariavel(VariavelTAM variavel) {

    }

    @Override
    public List<VariavelTAM> findAllVariaveisFromAnalise(int idAnalise) {
        Query query = getSession().createSQLQuery(
                "select * from variavelTAM v where v.idAnalise = :idAnalise")
                .addEntity(VariavelTAM.class)
                .setParameter("idAnalise", idAnalise);
        return (List<VariavelTAM>) query.list();
    }

    @Override
    public void deleteVariavel(VariavelTAM variavel) {

    }

    @Override
    public VariavelTAM findById(Integer id) {
        return null;
    }

    @Override
    public void updateVariavel(VariavelTAM variavel) {

    }
}
