package ua.goit.java.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.model.ListOfIngredients;

import java.util.List;

public class ListOfIngredientsDAO {

    private SessionFactory sessionFactory;

    public void save(ListOfIngredients listOfIngredients) {
        Session session = sessionFactory.getCurrentSession();
        session.save(listOfIngredients);

    }

    public void remove(ListOfIngredients listOfIngredients) {
        sessionFactory.getCurrentSession().delete(listOfIngredients);

    }

    public void removeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from ListOfIngredients i where i.dishID = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public ListOfIngredients findByName() {
        return null;
    }

    public List<ListOfIngredients> findAll() {
        return null;
    }

    @Transactional
    public List<ListOfIngredients> findByDishId(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select l from ListOfIngredients l where l.dishID = :id");
        query.setParameter("id", id);
        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
