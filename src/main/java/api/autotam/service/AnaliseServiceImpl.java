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
        analiseDAO.saveAnalise(analise);
    }

    public void addVariavelToAnalise(int idAnalise, VariavelTAM variavel){
        Analise analise = findById(idAnalise);
        variavel.setAnalise(analise);
        List<VariavelTAM> variaveis = analise.getVariaveis();
        variaveis.add(variavel);
        analise.setVariaveis(variaveis);
        updateAnalise(analise);
    }

    @Override
    public List<Permissao> findAllAnalises(int idUsuario){
        List<Permissao> permissoes = permissaoDAO.findAllPermissoesByUsuario(idUsuario);
        return permissoes;
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
