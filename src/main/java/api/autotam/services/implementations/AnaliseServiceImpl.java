package api.autotam.services.implementations;

import api.autotam.daos.interfaces.AnaliseDAO;
import api.autotam.daos.interfaces.PermissaoDAO;
import api.autotam.daos.interfaces.VariavelTAMDAO;
import api.autotam.model.Analise;
import api.autotam.model.Permissao;
import api.autotam.model.VariavelTAM;
import api.autotam.services.interfaces.AnaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Danilo on 9/11/2016.
 */
@Service("analiseService")
@Transactional
public class AnaliseServiceImpl extends AbstractService implements AnaliseService {

    @Autowired
    private PermissaoDAO permissaoDAO;

    @Autowired
    private AnaliseDAO analiseDAO;

    @Autowired
    private VariavelTAMDAO variavelTAMDAO;

    @Override
    public void saveAnalise(Analise analise) {
        Permissao administrador = new Permissao(getUsuarioLogado(), analise, true, true);
        analise.setStatus("Avaliação das Questões");
        administrador.setAnalise(analise);
        permissaoDAO.savePermissao(administrador);
        analiseDAO.saveAnalise(analise);
    }

    @Override
    public void addVariavelToAnalise(int idAnalise, VariavelTAM variavel){
        Analise analise = findById(idAnalise);
        variavel.setAnalise(analise);
        List<VariavelTAM> variaveis = analise.getVariaveis();
        variaveis.add(variavel);
        analise.setVariaveis(variaveis);
        updateAnalise(analise);
    }

    @Override
    public Analise findById(int idAnalise) {
        return analiseDAO.findById(idAnalise);
    }

    @Override
    public void deleteAnalise(int idAnalise) {
        analiseDAO.deleteAnalise(idAnalise);
    }

    @Override
    public void updateAnalise(Analise analise) {analiseDAO.updateAnalise(analise);}

    @Override
    public boolean isAnaliseExist(Analise analise) {
        return findById(analise.getIdAnalise())!=null;
    }

}
