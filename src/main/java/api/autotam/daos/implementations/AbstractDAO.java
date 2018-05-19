package api.autotam.daos.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DAO abstrado responsável por encapsular operações básicas no Banco de Dados.
 *
 * @author Danilo
 */

public abstract class AbstractDAO{

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void persist(Object entity) {
        getSession().persist(entity);
    }

    public void merge(Object entity) {
        getSession().merge(entity);
    }

    public void saveOrUpdate(Object entity) {
        getSession().saveOrUpdate(entity);
    }

    public void delete(Object entity) {
        getSession().delete(entity);
    }



}
