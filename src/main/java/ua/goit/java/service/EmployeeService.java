package ua.goit.java.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.dao.EmployeeDAO;
import ua.goit.java.dao.model.Employee;
import ua.goit.java.dao.model.Position;
import ua.goit.java.dao.model.Waiter;

import java.util.List;

public class EmployeeService {

    private EmployeeDAO employeeDao;

    public List<Employee> getEmployees(){
        return employeeDao.findAll();
    }

    @Transactional
    public List<Employee> getEmployeesByName(String employeeName) {
        return employeeDao.findByName1(employeeName);
    }

    @Transactional
    public void updateEmployeeInfo(int id, Employee employeeWithNewInformation) {
        employeeDao.updateEmployee(id, employeeWithNewInformation);
    }

    @Transactional
    public Employee getEmployeeByID(int id) {
        return employeeDao.findById(id);
    }

    public List<Waiter> getAllWaiters() {
        return employeeDao.findAllWaiters();
    }

    @Transactional
    public void deleteEmployee(Employee employee) {
        employeeDao.remove(employee);
    }

    public boolean checkValidPhoneNumber(String phoneNumber) {

        boolean result = true;

        if (phoneNumber.length() != 10) result = false;

        if (phoneNumber.charAt(0) != '0') result = false;

        return result;
    }

    @Transactional
    public void employeeWithoutPhoneNumber(String firstName, String lastName, String dateOfBirth, double salary, ModelAndView modelAndView) {

        Employee employee = new Employee();

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDateOfBirth(dateOfBirth);
        employee.setSalary(salary);
        modelAndView.addObject("employee", employee);
    }


    @Transactional
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

        employeeDao.save(employee);
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
            employee.setPosition(Position.WAITER);
        } else if (position.equals("Cook")) {
            employee.setPosition(Position.COOK);
        } else if (position.equals("Manager")) {
            employee.setPosition(Position.MANAGER);
        } else if (position.equals("Cleaner")) {
            employee.setPosition(Position.CLEANER);
        }
    }

    public void setEmployeeDao(EmployeeDAO employeeDao) {
        this.employeeDao = employeeDao;
    }
}
