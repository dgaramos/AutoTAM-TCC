package api.autotam.service;

import api.autotam.dao.PermissaoDAO;
import api.autotam.model.Permissao;
import api.autotam.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Danilo on 9/11/2016.
 */
@Service("permissaoService")
@Transactional
public class AnaliseServiceImpl implements AnaliseService {

    @Autowired
    private PermissaoDAO permissaoDAO;

    public List<Permissao> findAllAnalises(int idUsuario){
        List<Permissao> permissoes = permissaoDAO.findAllPermissoesByUsuario(idUsuario);
        return permissoes;
    }


}
