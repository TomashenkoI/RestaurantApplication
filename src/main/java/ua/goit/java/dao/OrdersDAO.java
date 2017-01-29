package ua.goit.java.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.model.Employee;
import ua.goit.java.dao.model.Orders;

import java.util.List;

public class OrdersDAO {

    private SessionFactory sessionFactory;

    @Transactional
    public void save(Orders orders) {
        Session session = sessionFactory.getCurrentSession();
        session.save(orders);
    }

    @Transactional
    public void remove(Orders orders) {
        sessionFactory.getCurrentSession().delete(orders);
    }

    public Orders findById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select o from Orders o where o.ID = :id");
        query.setParameter("id", id);

        return (Orders) query.uniqueResult();
    }


    public List<Orders> findAllOpenedOrders() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select o from Orders o where o.access = true").list();
    }

    public List<Orders> getOrdersByTableNumber(int tableNumber) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select o from Orders o where o.tableNumber = :tableNumber");
        query.setParameter("tableNumber", tableNumber);

        return query.list();
    }

    public List<Orders> getOrdersByWaiter(Employee employee) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select o from Orders o where o.employee = :id");
        query.setParameter("id", employee);

        return query.list();
    }

    public List<Orders> getOrdersByDate(String date) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select o from Orders o where o.date like :date");
        query.setParameter("date", date+"%");

        return query.list();
    }

    @Transactional
    public List<Orders> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select o from Orders o").list();
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}

