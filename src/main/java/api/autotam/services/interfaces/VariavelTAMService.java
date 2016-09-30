package api.autotam.services.interfaces;

import api.autotam.model.VariavelTAM;

import java.util.List;

/**
 * Created by Danilo on 9/25/2016.
 */
public interface VariavelTAMService {

    void saveVariavel(VariavelTAM variavel);

    List<VariavelTAM> findAllVariaveisFromAnalise(int idAnalise);

    VariavelTAM findById(int idVariavel);

    void deleteVariavel(int idVariavel);

    void updateVariavel(VariavelTAM variavel);

    boolean isVariavelExist(VariavelTAM variavel);
}
