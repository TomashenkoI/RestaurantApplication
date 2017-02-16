package ua.goit.java.dao.Impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.goit.java.dao.OrdersDAO;
import ua.goit.java.dao.model.Employee;
import ua.goit.java.dao.model.Orders;

import java.util.List;

@Repository
public class OrdersDAOImpl extends GenericDAOImpl<Orders, Integer> implements OrdersDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public List<Orders> getOrdersByTableNumber(int tableNumber) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select o from Orders o where o.tableNumber = :tableNumber");
        query.setParameter("tableNumber", tableNumber);

        return query.list();
    }

    public List<Orders> getOrdersByWaiter(Employee waiter) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select o from Orders o where o.employee = :waiter");
        query.setParameter("waiter", waiter);

        return query.list();
    }

    public List<Orders> getOrdersByDate(String date) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select o from Orders o where o.date like :date");
        query.setParameter("date", date+"%");

        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}

