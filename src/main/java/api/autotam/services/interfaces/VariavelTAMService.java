package api.autotam.services.interfaces;

import api.autotam.model.Questao;
import api.autotam.model.VariavelTAM;

import java.util.List;

/**
 * Created by Danilo on 9/25/2016.
 */
public interface VariavelTAMService {

    void saveVariavel(VariavelTAM variavel);

    VariavelTAM findById(int idVariavel);

    void updateVariavel(VariavelTAM variavel);

    void deleteVariavel(int idVariavel);

    boolean isVariavelExist(VariavelTAM variavel);

    List<VariavelTAM> findAllVariaveisFromAnalise(int idAnalise);

}
