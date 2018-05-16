package api.autotam.daos.interfaces;

import api.autotam.model.OpcaoDeObjeto;

import java.util.List;

public interface OpcaoDeObjetoDAO {

    void saveOpcaoDeObjeto(OpcaoDeObjeto opcaoDeObjeto);

    OpcaoDeObjeto findById(int idOpcaoDeObjeto);

    void updateOpcaoDeObjeto(OpcaoDeObjeto opcaoDeObjeto);

    void deleteOpcaoDeObjeto(int idOpcaoDeObjeto);

    List<OpcaoDeObjeto> findAllOpcoesDeObjetoFromAnalise(int idAnalise);
}
