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
 * Classe de serviço responsável por encapsular as regras de negócio referentes as Análises TAM da aplicação.
 *
 * @author Danilo
 */

@Service("analiseService")
@Transactional
public class AnaliseServiceImpl extends AbstractService implements AnaliseService {

    @Autowired
    private PermissaoDAO permissaoDAO;

    @Autowired
    private AnaliseDAO analiseDAO;

    /**
     * Método responsável pela operação de cadastro de uma Análise, gerando uma Permissão para o Usuário em sessão
     * que está criando a Análise.
     *
     * @param analise
     */
    @Override
    public void saveAnalise(Analise analise) {
        Permissao administrador = new Permissao(getUsuarioLogado(), analise, true, true);
        analise.setStatus("Avaliação das Questões");
        administrador.setAnalise(analise);
        permissaoDAO.savePermissao(administrador);
        analiseDAO.saveAnalise(analise);
    }

    /**
     * Método responsável pela operação de busca de uma determinada Análise por meio de seu id verificando
     * se o Usuário em sessão tem permissão para visualizar a Análise.
     *
     * @param idAnalise
     * @return
     */
    @Override
    public Analise findById(int idAnalise) {
        if(usuarioLogadoHasPermissão(idAnalise)) {
            return analiseDAO.findById(idAnalise);
        }else{
            throw new SecurityException("Usuario não tem permissão para essa análise");
        }
    }

    /**
     * Método responsável pela operação de atualização das informações de uma determinada Análise verificando se o
     * Usuário em sessão possui permissão de Administrador para concluir a operação.
     *
     * @param analise
     */
    @Override
    public void updateAnalise(Analise analise) {
        if (usuarioLogadoIsAdministrador(analise.getIdAnalise())) {
            analiseDAO.updateAnalise(analise);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    /**
     * Método responsável por apagar as informações de uma determinada Análise verificando se o Usuário
     * em sessão possui permissão de Administrador para concluir a operação.
     *
     * @param idAnalise
     */
    @Override
    public void deleteAnalise(int idAnalise) {
        if (usuarioLogadoIsAdministrador(idAnalise)){
            analiseDAO.deleteAnalise(idAnalise);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    /**
     * Método responsável por verificar se uma determinada Análise existe no Banco de Dados verificando
     * se o Usuário em sessão tem permissão para visualizar a Análise.
     *
     * @param analise
     * @return
     */
    @Override
    public boolean isAnaliseExist(Analise analise) {
        if(usuarioLogadoHasPermissão(analise.getIdAnalise())) {
            return findById(analise.getIdAnalise()) != null;
        }else{
            throw new SecurityException("Usuario não tem permissão para essa análise");
        }

    }

    /**
     * Método responsável pela operação de cadastro de uma Variável TAM em uma determinada Análise, verificando se o
     * Usuário em sessão possui permissão de Administrador para concluir a operação.
     *
     * @param idAnalise
     * @param variavel
     */
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
