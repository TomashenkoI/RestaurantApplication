//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//import ua.goit.java.dao.model.Dish;
//import ua.goit.java.dao.model.Orders;
//import ua.goit.java.service.DishService;
//import ua.goit.java.service.OrderService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@ContextConfiguration(locations = "classpath:application-context-test.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
//public class OrderServiceTest {
//
//    @Autowired
//    private DishService dishService;
//
//    @Autowired
//    private OrderService orderService;
//
//    @Before
//    public void setUp() throws Exception {
//        List<Orders> all = orderService.getAllOrders();
//        all.stream().forEach(e -> orderService.deleteOrderById(e.getID()));
//
//        Dish dish = new Dish();
//        dish.setName("test dish");
//        dishService.save(dish);
//
//        Orders order = new Orders();
//        order.setAccess(true);
//        orderService.save(order);
//
//        Orders order2 = new Orders();
//        order2.setAccess(false);
//        orderService.save(order2);
//    }
//
//    @Test
//    public void save() throws Exception {
//        Orders order = new Orders();
//        order.setAccess(false);
//        orderService.save(order);
//
//        List<Orders> all = orderService.getAllOrders();
//        Assert.assertEquals(all.size(), 3);
//    }
//
//    @Test
//    public void delete() throws Exception {
//        orderService.delete(2);
//
//        List<Orders> all = orderService.getAllOrders();
//        Assert.assertEquals(all.size(), 1);
//    }
//
//    @Test
//    public void getById() throws Exception {
//        List<Orders> all = orderService.getAllOrders();
//        Orders order = all.get(0);
//        String expectedDate = order.getDate();
//
//        order = orderService.getOrderById(order.getID());
//
//        Assert.assertEquals(order.getDate(), expectedDate);
//    }
//
//    @Test
//    public void getAll() throws Exception {
//        List<Orders> all = orderService.getAllOrders();
//        Assert.assertEquals(all.size(), 2);
//    }
//
//    @Test
//    @Ignore
//    public void getOpen() throws Exception {
//        List<Orders> all = orderService.getOpen();
//        Assert.assertEquals(all.size(), 1);
//    }
//}