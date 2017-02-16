package ua.goit.java.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.dao.model.Employee;
import ua.goit.java.service.EmployeeService;
import ua.goit.java.service.OrderService;

import java.io.File;
import java.util.Map;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;
    private OrderService orderService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ModelAndView employees(){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("employees", employeeService.getEmployees());

        return modelAndView;
    }

    @RequestMapping(value = "/admin/employees", method = RequestMethod.GET)
    public String adminEmployees(Map<String, Object> model){
        model.put("employees", employeeService.getEmployees());

        return "admin/employees";
    }

    @RequestMapping(value = "/admin/findEmployeeByName", method = RequestMethod.GET)
    public ModelAndView adminFindEmployeesByName(@RequestParam("employeeName") String employeeName) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("employeeName", employeeName);
        modelAndView.addObject("employees", employeeService.getEmployeesByName(employeeName));
        modelAndView.setViewName("admin/employees");

        return modelAndView;
    }

    @RequestMapping(value = "/findEmployeeByName", method = RequestMethod.GET)
    public ModelAndView findEmployeesByName(@RequestParam("employeeName") String employeeName) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("employeeName", employeeName);
        modelAndView.addObject("employees", employeeService.getEmployeesByName(employeeName));
        modelAndView.setViewName("/employees");

        return modelAndView;
    }

    @RequestMapping(value = "admin/employees", method = RequestMethod.POST)
    public ModelAndView addNewEmployee(@RequestParam("employeeName") String firstName,
                                       @RequestParam("employeeSurname") String lastName,
                                       @RequestParam("employeePhoneNumber") String phoneNumber,
                                       @RequestParam("employeeDOB") String dateOfBirth,
                                       @RequestParam("employeePosition") String position,
                                       @RequestParam("employeeSalary") double salary)
    {
        ModelAndView modelAndView = new ModelAndView();

        boolean correctPhoneNumber = employeeService.checkValidPhoneNumber(phoneNumber);

        System.out.println(correctPhoneNumber);

        if (correctPhoneNumber) {

            employeeService.addNewEmployee(firstName, lastName, phoneNumber, dateOfBirth, salary, position);

            modelAndView.addObject("employees", employeeService.getEmployees());

            modelAndView.setViewName("admin/employees");

            return modelAndView;

        } else {

            employeeService.employeeWithoutPhoneNumber(firstName, lastName, dateOfBirth, salary, modelAndView);

            modelAndView.addObject("doesItAlreadyExist", false);

            modelAndView.addObject("correctPhoneNumber", correctPhoneNumber);

            modelAndView.setViewName("admin/newEmployee");

            return modelAndView;
        }
    }

    @RequestMapping(value = "/employee/employeeId={employeeId}", method = RequestMethod.GET)
    public ModelAndView employee(@PathVariable int employeeId){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("employee", employeeService.getEmployeeByID(employeeId));

        modelAndView.setViewName("employee");

        setPictureForEmployee(employeeId, modelAndView);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/employee/employeeId={employeeId}", method = RequestMethod.GET)
    public ModelAndView adminEmployee(@PathVariable int employeeId){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("employee", employeeService.getEmployeeByID(employeeId));

        modelAndView.setViewName("admin/employee");

        setPictureForEmployee(employeeId, modelAndView);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete_EmployeeId={employeeID}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable int employeeID) {

        ModelAndView modelAndView = new ModelAndView();

        boolean thereIsNoOrdersWithThisWaiter = orderService.IsThereNoOrdersWithThisEmployee(employeeService.getEmployeeByID(employeeID));

        if (thereIsNoOrdersWithThisWaiter) {

            employeeService.deleteEmployee(employeeService.getEmployeeByID(employeeID));

            modelAndView.addObject("employees", employeeService.getEmployees());

            modelAndView.setViewName("admin/employees");

        } else {

            modelAndView.addObject("alertNeed", true);
            modelAndView.addObject("employee", employeeService.getEmployeeByID(employeeID));

            modelAndView.setViewName("admin/employee");

        }

        return modelAndView;
    }

    @RequestMapping(value = "/admin/newEmployee", method = RequestMethod.GET)
    public ModelAndView addNewEmployee() {

        ModelAndView modelAndView = new ModelAndView();

        boolean doesItAlreadyExist = false;
        modelAndView.addObject("doesItAlreadyExist", doesItAlreadyExist);

        modelAndView.setViewName("admin/newEmployee");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/update_EmployeeId={employeeID}", method = RequestMethod.GET)
    public ModelAndView updateEmployee(@PathVariable String employeeID) {

        ModelAndView modelAndView = new ModelAndView();

        boolean doesItAlreadyExist = true;
        modelAndView.addObject("doesItAlreadyExist", doesItAlreadyExist);

        modelAndView.addObject("employee", employeeService.getEmployeeByID(Integer.parseInt(employeeID)));

        modelAndView.setViewName("admin/newEmployee");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/updatedEmployeeId={employeeId}", method = RequestMethod.POST)
    public ModelAndView updateExistingEmployee(@PathVariable int employeeId,
                                             @RequestParam("employeeName") String firstName,
                                             @RequestParam("employeeSurname") String lastName,
                                             @RequestParam("employeePhoneNumber") String phoneNumber,
                                             @RequestParam("employeeDOB") String dateOfBirth,
                                             @RequestParam("employeePosition") String position,
                                             @RequestParam("employeeSalary") double salary)
    {


        ModelAndView modelAndView = new ModelAndView();

        Employee employeeWithNewInformation = employeeService.setInformation(firstName, lastName, phoneNumber, dateOfBirth,
                                                                             position, salary);

        employeeService.updateEmployeeInfo(employeeId, employeeWithNewInformation);

        modelAndView.addObject("employee", employeeService.getEmployeeByID(employeeId));

        setPictureForEmployee(employeeId, modelAndView);

        modelAndView.setViewName("admin/employee");

        return modelAndView;
    }

    private void setPictureForEmployee(@PathVariable int employeeId, ModelAndView modelAndView) {
        String path = "D:\\edu\\IdeaProjects\\Restaurant_MVC\\src\\main\\webapp\\resources\\images\\employees\\id"+employeeId+".jpg";
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {
            path = "/images/employees/id"+employeeId+".jpg";
            modelAndView.addObject("picturePath", path);
        } else {
            path = "/images/employees/default.jpg";
            modelAndView.addObject("picturePath", path);
        }
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
