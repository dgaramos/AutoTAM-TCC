package api.autotam.service;

import api.autotam.dao.AnaliseDAO;
import api.autotam.dao.PermissaoDAO;
import api.autotam.dao.UsuarioDAO;
import api.autotam.model.Analise;
import api.autotam.model.Permissao;
import api.autotam.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
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

    public void saveAnalise(Analise analise) {
       Permissao administrador = new Permissao();

        administrador.setUsuario(getUsuarioLogado());
        administrador.setAdministrador(true);
        administrador.setTestador(true);
        administrador.setAnalise(analise);
        permissaoDAO.savePermissao(administrador);
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
    public boolean isAnaliseExist(Analise analise) {
        return findById(analise.getIdAnalise())!=null;
    }


}
