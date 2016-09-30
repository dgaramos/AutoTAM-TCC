package api.autotam.services.implementations;

import api.autotam.daos.interfaces.VariavelTAMDAO;
import api.autotam.model.Questao;
import api.autotam.model.VariavelTAM;
import api.autotam.services.interfaces.VariavelTAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Classe de serviço responsável por encapsular as regras de negócio referentes as Variáveis TAM da aplicação.
 *
 * @author Danilo
 */

@Service("variavelTAMService")
@Transactional
public class VariavelTAMServiceImpl extends AbstractService implements VariavelTAMService {

    @Autowired
    private VariavelTAMDAO variavelTAMDAO;

    @Override
    public void saveVariavel(VariavelTAM variavel) {
        if(usuarioLogadoIsAdministrador(variavel.getAnalise().getIdAnalise())){
            variavelTAMDAO.saveVariavel(variavel);
        }else{
            throw new SecurityException("O usuário não é administrador da análise");
        }
    }

    @Override
    public VariavelTAM findById(int idVariavel) {return variavelTAMDAO.findById(idVariavel);}

    @Override
    public void updateVariavel(VariavelTAM variavel) {
        if(usuarioLogadoIsAdministrador(variavel.getAnalise().getIdAnalise())){
            variavelTAMDAO.updateVariavel(variavel);
        }else{
            throw new SecurityException("O usuário não é administrador da análise");
        }
    }

    @Override
    public void deleteVariavel(int idVariavel) {
        if(usuarioLogadoIsAdministrador(findById(idVariavel).getAnalise().getIdAnalise())){
            variavelTAMDAO.deleteVariavel(idVariavel);
        }else{
            throw new SecurityException("O usuário não é administrador da análise");
        }
    }

    @Override
    public boolean isVariavelExist(VariavelTAM variavel) {return findById(variavel.getIdVariavel()) != null;}

    @Override
    public List<VariavelTAM> findAllVariaveisFromAnalise(int idAnalise) {
        List<VariavelTAM> variaveis = variavelTAMDAO.findAllVariaveisFromAnalise(idAnalise);
        return variaveis;
    }
}
