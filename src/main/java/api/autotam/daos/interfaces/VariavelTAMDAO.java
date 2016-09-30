package api.autotam.daos.interfaces;

import api.autotam.model.VariavelTAM;

import java.util.List;

/**
 * Created by Danilo on 9/20/2016.
 */
public interface VariavelTAMDAO {

    void saveVariavel(VariavelTAM variavel);

    VariavelTAM findById(int idVariavel);

    void updateVariavel(VariavelTAM variavel);

    void deleteVariavel(int idVariavel);

    List<VariavelTAM> findAllVariaveisFromAnalise(int idAnalise);

}
