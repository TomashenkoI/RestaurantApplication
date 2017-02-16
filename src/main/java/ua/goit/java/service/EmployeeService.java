package ua.goit.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.dao.EmployeeDAO;
import ua.goit.java.dao.Impl.EmployeeDAOImpl;
import ua.goit.java.dao.model.Employee;
import ua.goit.java.dao.model.EmployeePosition;
import ua.goit.java.dao.model.Waiter;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDao;

    public List<Employee> getEmployees(){
        return employeeDao.findAll();
    }

    public List<Employee> getEmployeesByName(String employeeName) {
        return employeeDao.findByName(employeeName);
    }

    public void updateEmployeeInfo(int id, Employee employeeWithNewInformation) {

        Employee employee = employeeDao.read(id);

        employee.setFirstName(employeeWithNewInformation.getFirstName());
        employee.setLastName(employeeWithNewInformation.getFirstName());
        employee.setEmployeePosition(employeeWithNewInformation.getEmployeePosition());
        employee.setPhoneNumber(employeeWithNewInformation.getPhoneNumber());
        employee.setSalary(employeeWithNewInformation.getSalary());
        employee.setDateOfBirth(employeeWithNewInformation.getDateOfBirth());

        employeeDao.update(employee);
    }

    public Employee getEmployeeByID(int id) {
        return employeeDao.read(id);
    }

    public List<Waiter> getAllWaiters() {
        return employeeDao.findAllWaiters();
    }

    public void deleteEmployee(Employee employee) {
        employeeDao.delete(employee);
    }

    public boolean checkValidPhoneNumber(String phoneNumber) {

        boolean result = true;

        if (phoneNumber.length() != 10) result = false;

        if (phoneNumber.charAt(0) != '0') result = false;

        return result;
    }

    public void employeeWithoutPhoneNumber(String firstName, String lastName, String dateOfBirth, double salary, ModelAndView modelAndView) {

        Employee employee = new Employee();

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDateOfBirth(dateOfBirth);
        employee.setSalary(salary);
        modelAndView.addObject("employee", employee);
    }

    public void addNewEmployee(String firstName, String lastName, String phoneNumber, String dateOfBirth, double salary,
                               String position) {

        Employee employee = new Employee();

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPhoneNumber(phoneNumber);
        employee.setDateOfBirth(dateOfBirth);
        employee.setSalary(salary);
        setEmployeePosition(position, employee);
        employee.setPhotoURL("/images/employees/default.jpg");

        employeeDao.create(employee);
    }

    public Employee setInformation(String name, String surname, String phoneNumber,
                                   String dob, String position, double salary) {

        Employee employee = new Employee();
        employee.setFirstName(name);
        employee.setLastName(surname);
        employee.setPhoneNumber(phoneNumber);
        employee.setDateOfBirth(dob);
        employee.setSalary(salary);

        setEmployeePosition(position, employee);

        return employee;
    }

    public void setEmployeePosition(String position, Employee employee) {
        if (position.equals("Waiter")) {
            employee.setEmployeePosition(EmployeePosition.WAITER);
        } else if (position.equals("Cook")) {
            employee.setEmployeePosition(EmployeePosition.COOK);
        } else if (position.equals("Manager")) {
            employee.setEmployeePosition(EmployeePosition.MANAGER);
        } else if (position.equals("Cleaner")) {
            employee.setEmployeePosition(EmployeePosition.CLEANER);
        }
    }

    public void setEmployeeDao(EmployeeDAOImpl employeeDao) {
        this.employeeDao = employeeDao;
    }
}
