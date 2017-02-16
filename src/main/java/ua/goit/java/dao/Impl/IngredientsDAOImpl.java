package ua.goit.java.dao.Impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.goit.java.dao.IngredientsDAO;
import ua.goit.java.dao.model.Ingredient;

@Repository
public class IngredientsDAOImpl extends GenericDAOImpl<Ingredient, Integer> implements IngredientsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
