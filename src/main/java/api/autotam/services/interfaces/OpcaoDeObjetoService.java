package api.autotam.services.interfaces;

import api.autotam.model.OpcaoDeObjeto;

import java.util.List;

public interface OpcaoDeObjetoService{

    void saveOpcaoDeObjeto(OpcaoDeObjeto opcaoDeObjeto);

    OpcaoDeObjeto findById(int idOpcaoDeObjeto);

    void updateOpcaoDeObjeto(OpcaoDeObjeto opcaoDeObjeto);

    void deleteOpcaoDeObjeto(int idOpcaoDeObjeto);

    List<OpcaoDeObjeto> findAllOpcoesDeObjetoFromAnalise(int idAnalise);
}
