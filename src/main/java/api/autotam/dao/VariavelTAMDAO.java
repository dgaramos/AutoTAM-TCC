package api.autotam.dao;

import api.autotam.model.VariavelTAM;

import java.util.List;

/**
 * Created by Danilo on 9/20/2016.
 */
public interface VariavelTAMDAO {

    void saveVariavel(VariavelTAM variavel);

    List<VariavelTAM> findAllVariaveisFromAnalise(int idAnalise);

    void deleteVariavel(VariavelTAM variavel);

    VariavelTAM findById(Integer id);

    void updateVariavel(VariavelTAM variavel);

}