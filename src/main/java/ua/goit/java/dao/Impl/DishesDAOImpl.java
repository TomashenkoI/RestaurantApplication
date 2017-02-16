package ua.goit.java.dao.Impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.DishesDAO;
import ua.goit.java.dao.model.Dish;

import java.util.List;

@Repository
public class DishesDAOImpl extends GenericDAOImpl<Dish, Integer> implements DishesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Dish> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select d from Dish d").list();
    }

    @Transactional
    public List<Dish> findByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Dish d where d.name like :name");
        query.setParameter("name", "%"+name+"%");

        return query.list();

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
