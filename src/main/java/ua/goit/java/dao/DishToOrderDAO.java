package ua.goit.java.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
//import ua.goit.java.Controller.DishesController;
//import ua.goit.java.Controller.OrderController;
import ua.goit.java.dao.model.DishToOrder;

import java.util.List;

public class DishToOrderDAO{

    private SessionFactory sessionFactory;

    @Transactional
    public void save(DishToOrder dishToOrder) {
        Session session = sessionFactory.getCurrentSession();
        session.save(dishToOrder);
    }

    public void remove(DishToOrder dishToOrder) {
        sessionFactory.getCurrentSession().delete(dishToOrder);
    }

    public List<DishToOrder> findAll() {
        return null;
    }

    public DishToOrder findByName() {
        return null;
    }

    @Transactional
    public void removeById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        final Query query = session.createQuery("delete from DishToOrder i where i.orderId = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}

