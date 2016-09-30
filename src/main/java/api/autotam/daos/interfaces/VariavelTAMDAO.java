package api.autotam.daos.interfaces;

import api.autotam.model.VariavelTAM;

import java.util.List;

/**
 * Created by Danilo on 9/20/2016.
 */
public interface VariavelTAMDAO {

    void saveVariavel(VariavelTAM variavel);

    void updateVariavel(VariavelTAM variavel);

    List<VariavelTAM> findAllVariaveisFromAnalise(int idAnalise);

    void deleteVariavel(int idVariavel);

    VariavelTAM findById(int idVariavel);

}
