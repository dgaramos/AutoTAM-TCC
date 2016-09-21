package api.autotam.service;

import api.autotam.dao.AnaliseDAO;
import api.autotam.dao.PermissaoDAO;
import api.autotam.dao.UsuarioDAO;
import api.autotam.dao.VariavelTAMDAO;
import api.autotam.model.Analise;
import api.autotam.model.Permissao;
import api.autotam.model.Usuario;
import api.autotam.model.VariavelTAM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
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

    public void saveAnalise(Analise analise) {
        Permissao administrador = new Permissao(getUsuarioLogado(), analise, true, true);
        analise.setStatus("Avaliação das Questões");
        administrador.setAnalise(analise);
        permissaoDAO.savePermissao(administrador);

        List<VariavelTAM> variaveis = new ArrayList<VariavelTAM>();

        VariavelTAM peou = new VariavelTAM("Facilidade de Uso Percebida", analise);
        variaveis.add(peou);
        variavelTAMDAO.saveVariavel(peou);

        VariavelTAM pu = new VariavelTAM("Utilidade Percebida", analise);
        variaveis.add(pu);
        variavelTAMDAO.saveVariavel(pu);

        if(analise.getTipoAnalise() == "TAM2"){
            VariavelTAM pue = new VariavelTAM("Uso Percebido", analise);
            variaveis.add(pue);
            variavelTAMDAO.saveVariavel(pue);
        }else{
            analise.setTipoAnalise("TAM1");
        }

        analise.setVariaveis(variaveis);
        analiseDAO.saveAnalise(analise);
    }

    @Override
    public List<Permissao> findAllAnalises(int idUsuario){
        List<Permissao> permissoes = permissaoDAO.findAllPermissoesByUsuario(idUsuario);
        return permissoes;
    }

    @Override
    public Analise findById(Integer id) {
        return analiseDAO.findById(id);
    }


    @Override
    public void deleteAnalise(Analise analise) {
        analiseDAO.deleteAnalise(analise);
    }

    @Override
    public void updateAnalise(Analise analise) {
        analiseDAO.updateAnalise(analise);

    }

    @Override
    public boolean isAnaliseExist(Analise analise) {
        return findById(analise.getIdAnalise())!=null;
    }

}
