package api.autotam.daos.implementations;

import api.autotam.daos.interfaces.OpcaoDeObjetoDAO;
import api.autotam.model.OpcaoDeObjeto;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("opcaoDeObjetoDAO")
public class OpcaoDeObjetoDAOImpl extends AbstractDAO implements OpcaoDeObjetoDAO {
    @Override
    public void saveOpcaoDeObjeto(OpcaoDeObjeto opcaoDeObjeto) {
        persist(opcaoDeObjeto);
    }

    @Override
    public OpcaoDeObjeto findById(int idOpcaoDeObjeto) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM opcaoDeObjeto o WHERE o.idOpcaoDeObjeto = :idOpcaoDeObjeto")
                .addEntity(OpcaoDeObjeto.class)
                .setParameter("idOpcaoDeObjeto", idOpcaoDeObjeto);
        return (OpcaoDeObjeto) query.uniqueResult() ;
    }

    @Override
    public void updateOpcaoDeObjeto(OpcaoDeObjeto opcaoDeObjeto) {
        getSession().update(opcaoDeObjeto);
    }

    @Override
    public void deleteOpcaoDeObjeto(int idOpcaoDeObjeto) {
        Query query = getSession().createSQLQuery(
                "DELETE FROM opcaoDeObjeto WHERE idOpcaoDeObjeto = :idOpcaoDeObjeto");
        query.setParameter("idOpcaoDeObjeto", idOpcaoDeObjeto);
        query.executeUpdate();
    }

    @Override
    public List<OpcaoDeObjeto> findAllOpcoesDeObjetoFromAnalise(int idAnalise) {
        Query query = getSession().createSQLQuery(
                "SELECT * FROM opcaoDeObjeto o WHERE o.idAnalise = :idAnalise")
                .addEntity(OpcaoDeObjeto.class)
                .setParameter("idAnalise", idAnalise);
        return (List<OpcaoDeObjeto>) query.list() ;
    }
}
