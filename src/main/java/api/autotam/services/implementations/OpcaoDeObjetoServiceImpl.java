package api.autotam.services.implementations;

import api.autotam.daos.interfaces.OpcaoDeObjetoDAO;
import api.autotam.model.OpcaoDeObjeto;
import api.autotam.services.interfaces.OpcaoDeObjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service("opcaoDeObjetoService")
@Transactional
public class OpcaoDeObjetoServiceImpl extends AbstractService implements OpcaoDeObjetoService {

    @Autowired
    private OpcaoDeObjetoDAO opcaoDeObjetoDAO;

    @Override
    public void saveOpcaoDeObjeto(OpcaoDeObjeto opcaoDeObjeto) {
        opcaoDeObjetoDAO.saveOpcaoDeObjeto(opcaoDeObjeto);
    }

    @Override
    public OpcaoDeObjeto findById(int idOpcaoDeObjeto) {
       return opcaoDeObjetoDAO.findById(idOpcaoDeObjeto);
    }

    @Override
    public void updateOpcaoDeObjeto(OpcaoDeObjeto opcaoDeObjeto) {
        opcaoDeObjetoDAO.updateOpcaoDeObjeto(opcaoDeObjeto);
    }

    @Override
    public void deleteOpcaoDeObjeto(int idOpcaoDeObjeto) {
        opcaoDeObjetoDAO.deleteOpcaoDeObjeto(idOpcaoDeObjeto);
    }

    @Override
    public List<OpcaoDeObjeto> findAllOpcoesDeObjetoFromAnalise(int idAnalise) {
        return opcaoDeObjetoDAO.findAllOpcoesDeObjetoFromAnalise(idAnalise);
    }
}
