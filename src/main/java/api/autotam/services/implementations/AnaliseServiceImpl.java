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

    @Override
    public void saveAnalise(Analise analise) {
        Permissao administrador = new Permissao(getUsuarioLogado(), analise, true, true);
        analise.setStatus("Avaliação das Questões");
        administrador.setAnalise(analise);
        permissaoDAO.savePermissao(administrador);
        analiseDAO.saveAnalise(analise);
    }

    @Override
    public Analise findById(int idAnalise) {
        return analiseDAO.findById(idAnalise);
    }


    @Override
    public void updateAnalise(Analise analise) {
        if (usuarioLogadoIsAdministrador(analise.getIdAnalise())) {
            analiseDAO.updateAnalise(analise);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    @Override
    public void deleteAnalise(int idAnalise) {
        if (usuarioLogadoIsAdministrador(idAnalise)){
            analiseDAO.deleteAnalise(idAnalise);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    @Override
    public boolean isAnaliseExist(Analise analise) {
        return findById(analise.getIdAnalise())!=null;
    }

    @Override
    public void addVariavelToAnalise(int idAnalise, VariavelTAM variavel){
        if (usuarioLogadoIsAdministrador(idAnalise)){
            Analise analise = findById(idAnalise);
            variavel.setAnalise(analise);
            List<VariavelTAM> variaveis = analise.getVariaveis();
            variaveis.add(variavel);
            analise.setVariaveis(variaveis);
            updateAnalise(analise);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }

    }

}
