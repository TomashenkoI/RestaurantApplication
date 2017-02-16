package ua.goit.java.dao;

import ua.goit.java.dao.model.Employee;
import ua.goit.java.dao.model.Waiter;

import java.util.List;

public interface EmployeeDAO extends GenericDAO<Employee, Integer> {

    List<Employee> findByName(String name);
    List<Waiter> findAllWaiters();

}
