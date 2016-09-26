package api.autotam.service;

import api.autotam.dao.VariavelTAMDAO;
import api.autotam.model.VariavelTAM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Danilo on 9/25/2016.
 */

@Service("variavelTAMService")
@Transactional
public class VariavelTAMServiceImpl extends AbstractService implements VariavelTAMService {

    @Autowired
    private VariavelTAMDAO variavelTAMDAO;

    @Override
    public void saveVariavel(VariavelTAM variavel) {
        variavelTAMDAO.saveVariavel(variavel);
    }

    @Override
    public List<VariavelTAM> findAllVariaveisFromAnalise(int idAnalise) {
        List<VariavelTAM> variaveis = variavelTAMDAO.findAllVariaveisFromAnalise(idAnalise);
        return variaveis;
    }

    @Override
    public VariavelTAM findById(int idVariavel) {return variavelTAMDAO.findById(idVariavel);}

    @Override
    public void deleteVariavel(int idVariavel) {
        variavelTAMDAO.deleteVariavel(idVariavel);
    }

    @Override
    public void updateVariavel(VariavelTAM variavel) {variavelTAMDAO.updateVariavel(variavel);}

    @Override
    public boolean isVariavelExist(VariavelTAM variavel) {return findById(variavel.getIdVariavel()) != null;}
}
