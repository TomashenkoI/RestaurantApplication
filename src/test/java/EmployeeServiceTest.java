//package java;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//import ua.goit.java.dao.model.Employee;
//import ua.goit.java.service.EmployeeService;
//
//import java.util.List;
//
//@ContextConfiguration(locations = "classpath:application-context-test.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
//public class EmployeeServiceTest {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @Before
//    public void setUp() throws Exception {
//        List<Employee> all = employeeService.getEmployees();
//        all.stream().forEach(e -> employeeService.deleteEmployee(e));
//
//        Employee employee = new Employee();
//        employee.setFirstName("test employee");
//        employeeService.save(employee);
//
//        Employee employee2 = new Employee();
//        employee2.setFirstName("test employee 2");
//        employeeService.save(employee2);
//    }
//
//    @Test
//    public void save() throws Exception {
//        Employee employee = new Employee();
//        employee.setFirstName("test employee 3");
//        employeeService.save(employee);
//
//        List<Employee> all = employeeService.getEmployees();
//        Assert.assertEquals(all.size(), 3);
//    }
//
//    @Test
//    public void delete() throws Exception {
//        employeeService.delete(2);
//
//        List<Employee> all = employeeService.getEmployees();
//        Assert.assertEquals(all.size(), 1);
//    }
//
//    @Test
//    public void getByName() throws Exception {
//        List<Employee> all = employeeService.getEmployeesByName("test employee");
//        Assert.assertEquals(all.size(), 2);
//    }
//
//    @Test
//    public void getById() throws Exception {
//        List<Employee> all = employeeService.getEmployees();
//        Employee employee = all.get(0);
//        String expectedName = employee.getFirstName();
//
//        employee = employeeService.getEmployeeByID(employee.getID());
//
//        Assert.assertEquals(employee.getFirstName(), expectedName);
//    }
//
//    @Test
//    public void getAll() throws Exception {
//        List<Employee> all = employeeService.getEmployees();
//        Assert.assertEquals(all.size(), 2);
//    }
//
//}