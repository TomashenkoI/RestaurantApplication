package ua.goit.java.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.model.Employee;
import ua.goit.java.dao.model.Position;
import ua.goit.java.dao.model.Waiter;

import java.util.List;

public class EmployeeDAO {

    private SessionFactory sessionFactory;

    @Transactional
    public void save(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    public void remove(Employee employee){
        sessionFactory.getCurrentSession().delete(employee);
    }

    public List<Employee> findByName1(String name) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.firstName like :name");
        query.setParameter("name", "%"+name+"%");

        return query.list();

    }

    @Transactional
    public List<Employee> findAllCooks() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.position = :position");
        query.setParameter("position", Position.COOK);

        return query.list();

    }

    @Transactional
    public List<Waiter> findAllWaiters() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.position = :position");
        query.setParameter("position", Position.WAITER);

        return query.list();
    }

    public Employee findById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.ID = :id");
        query.setParameter("id", id);

        return (Employee) query.uniqueResult();

    }

    public Employee findByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.firstName like :name");
        query.setParameter("name", name);

        return (Employee) query.uniqueResult();

    }

    @Transactional
    public void updateEmployee(int employeeId, Employee newEmployee){
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, employeeId);

        if (newEmployee.getFirstName() != null) {
            employee.setFirstName(newEmployee.getFirstName());
        }
        if (newEmployee.getLastName() != null) {
            employee.setLastName(newEmployee.getLastName());
        }
        if (newEmployee.getPosition() != null) {
            employee.setPosition(newEmployee.getPosition());
        }
        if (newEmployee.getPhoneNumber() != null) {
            employee.setPhoneNumber(newEmployee.getPhoneNumber());
        }
        if (newEmployee.getDateOfBirth() != null) {
            employee.setDateOfBirth(newEmployee.getDateOfBirth());
        }
        if ((Double) newEmployee.getSalary() != null) {
            employee.setSalary(newEmployee.getSalary());
        }
        session.update(employee);
    }

    @Transactional
    public List<Employee> findAll() {

        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Employee e").list();

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
