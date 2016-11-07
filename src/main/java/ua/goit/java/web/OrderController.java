package ua.goit.java.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.dao.Requests;
import ua.goit.java.dao.model.Employee;
import ua.goit.java.service.EmployeeService;
import ua.goit.java.service.OrderService;

import java.util.ArrayList;

@Controller
public class OrderController {

    private OrderService orderService;
    private EmployeeService employeeService;

    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    public ModelAndView showAll() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("waiters", employeeService.getAllWaiters());
        modelAndView.addObject("orders", orderService.getAllOrders());

        modelAndView.setViewName("admin/orders");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/close_orderId={orderID}", method = RequestMethod.GET)
    public ModelAndView closeOrder(@PathVariable int orderID) {

        ModelAndView modelAndView = new ModelAndView();

        orderService.closeOrder(orderID);

        modelAndView.addObject("order", orderService.getOrderById(orderID));

        modelAndView.setViewName("admin/order");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/orders", method = RequestMethod.POST)
    public ModelAndView addOrder(@RequestParam int waiterId,
                                 @RequestParam int tableNumber,
                                 @RequestParam ArrayList selectedDishes) {

        ModelAndView modelAndView = new ModelAndView();

        String messageWhenCreatingOrder = orderService.enoughIngredients(selectedDishes);

        if (messageWhenCreatingOrder.equals("successful added!")) {

            orderService.creatingOrder(waiterId, tableNumber, selectedDishes);

            modelAndView.addObject("waiters", employeeService.getAllWaiters());
            modelAndView.addObject("orders", orderService.getAllOrders());

            modelAndView.setViewName("admin/orders");

        } else {

            boolean needAlert = true;

            modelAndView.addObject("message", messageWhenCreatingOrder);
            modelAndView.addObject("needAlert", needAlert);
            modelAndView.addObject("alertNeed", true);
            modelAndView.addObject("dishes", orderService.allDishes());
            modelAndView.addObject("waiters", employeeService.getAllWaiters());
            modelAndView.addObject("doesItAlreadyExist", false);

            modelAndView.setViewName("admin/newOrder");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/admin/newOrder", method = RequestMethod.GET)
    public ModelAndView newOrder() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("dishes", orderService.allDishes());
        modelAndView.addObject("waiters", employeeService.getAllWaiters());
        modelAndView.addObject("doesItAlreadyExist", false);

        modelAndView.setViewName("admin/newOrder");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/orders/orderId={orderId}", method = RequestMethod.GET)
    public ModelAndView getOrderById(@PathVariable int orderId) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("order", orderService.getOrderById(orderId));

        modelAndView.setViewName("admin/order");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/findOrdersByDate",method = RequestMethod.GET)
    public ModelAndView getOrdersByDate(@RequestParam String date) {

        ModelAndView modelAndView = new ModelAndView();

        System.out.println(date);

        Requests requests = new Requests();

        date = requests.parsedDate(date);

        modelAndView.addObject("waiters", employeeService.getAllWaiters());
        modelAndView.addObject("orders", orderService.getOrderByDate(date));

        modelAndView.setViewName("admin/orders");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/ordersByEmployee")
    public ModelAndView getOrdersByWaiter(@RequestParam int selectedWaiterId) {

        ModelAndView modelAndView = new ModelAndView();

        Employee employee = employeeService.getEmployeeByID(selectedWaiterId);

        modelAndView.addObject("waiters", employeeService.getAllWaiters());
        modelAndView.addObject("orders", orderService.getOrdersByWaiter(employee));

        modelAndView.setViewName("admin/orders");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete_orderId={orderId}")
    public ModelAndView deleteOrder(@PathVariable int orderId) {

        ModelAndView modelAndView = new ModelAndView();

        orderService.deleteOrderById(orderId);

        modelAndView.addObject("orders", orderService.getAllOrders());

        modelAndView.setViewName("admin/orders");

        return modelAndView;
    }
    @RequestMapping(value = "/admin/update_orderId={orderId}", method = RequestMethod.GET)
    public ModelAndView setInformationForOrderUpdate(@PathVariable int orderId) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("doesItAlreadyExist", true);
        modelAndView.addObject("waiters", employeeService.getAllWaiters());
        modelAndView.addObject("order", orderService.getOrderById(orderId));

        modelAndView.setViewName("admin/newOrder");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/update_orderId={orderId}", method = RequestMethod.POST)
    public ModelAndView updateOrder(@PathVariable int orderId,
                                    @RequestParam int tableNumber,
                                    @RequestParam int waiterId) {

        ModelAndView modelAndView = new ModelAndView();

        orderService.updateOrderInfo(orderId, tableNumber, waiterId);

        modelAndView.addObject("order", orderService.getOrderById(orderId));

        modelAndView.setViewName("admin/order");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/findOrdersByTableNumber", method = RequestMethod.GET)
    public ModelAndView getOrdersByTableNumber(@RequestParam int tableNumber) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("orders", orderService.getOrdersByTableNumber(tableNumber));
        modelAndView.addObject("waiters", employeeService.getAllWaiters());

        modelAndView.setViewName("admin/orders");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/addDish_OrderId={orderId}", method = RequestMethod.GET)
    public ModelAndView addAnotherDish(@PathVariable int orderId) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("needToAddTheDishToOrder", true);
        modelAndView.addObject("dishes", orderService.allDishes());
        modelAndView.addObject("orderId", orderId);

        modelAndView.setViewName("admin/dishes");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/addDish_orderId={orderId}&dishId={dishId}", method = RequestMethod.GET)
    public ModelAndView addSelectedDishToOrder(@PathVariable int orderId,
                                               @PathVariable int dishId) {

        ModelAndView modelAndView = new ModelAndView();

        orderService.addTheDishToOrder(orderId, dishId);

        modelAndView.addObject("order", orderService.getOrderById(orderId));
        modelAndView.addObject("waiters", employeeService.getAllWaiters());
        modelAndView.addObject("doesItAlreadyExist", true);

        modelAndView.setViewName("admin/newOrder");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/orders/orderId={orderId}/deleteDishId={dishId}", method = RequestMethod.GET)
    public ModelAndView deleteSelectedDishFromOrder(@PathVariable int orderId,
                                                    @PathVariable int dishId) {

        ModelAndView modelAndView = new ModelAndView();

        orderService.deleteDishFromOrder(orderId, dishId);

        modelAndView.addObject("order", orderService.getOrderById(orderId));

        modelAndView.setViewName("admin/order");

        return modelAndView;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
