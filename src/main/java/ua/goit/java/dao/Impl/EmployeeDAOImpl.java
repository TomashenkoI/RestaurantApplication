package ua.goit.java.dao.Impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.EmployeeDAO;
import ua.goit.java.dao.model.Employee;
import ua.goit.java.dao.model.EmployeePosition;
import ua.goit.java.dao.model.Waiter;

import java.util.List;

@Repository
public class EmployeeDAOImpl extends GenericDAOImpl<Employee, Integer> implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Employee> findByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.firstName like :name");
        query.setParameter("name", "%"+name+"%");

        return query.list();

    }

    @Transactional
    public List<Waiter> findAllWaiters() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.employeePosition = :position");
        query.setParameter("position", EmployeePosition.WAITER);

        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
