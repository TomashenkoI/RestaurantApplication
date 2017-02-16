package ua.goit.java.dao.Impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.goit.java.dao.StorageDAO;
import ua.goit.java.dao.model.Storage;

import java.util.List;

@Repository
public class StorageDAOImpl extends GenericDAOImpl<Storage, Integer> implements StorageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Storage> findByIngredientName(String ingredientName) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from Storage s where s.ingredient = :ingredient");
        query.setParameter("ingredient", "%"+ingredientName+"%");

        return query.list();

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
