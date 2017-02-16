package ua.goit.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.*;
import ua.goit.java.dao.Impl.DishesDAOImpl;
import ua.goit.java.dao.Impl.OrdersDAOImpl;
import ua.goit.java.dao.model.*;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.*;

import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;

@Service
public class OrderService {

    @Autowired
    private OrdersDAO ordersDAOImpl;

    @Autowired
    private DishesDAO dishesDAOImpl;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private StorageService storageService;

    public List<Dish> allDishes() {
        return dishesDAOImpl.findAll();
    }

    public List<Orders> getAllOrders() {
        return ordersDAOImpl.findAll();
    }

    @Transactional
    public Orders getOrderById(int id) {
        return ordersDAOImpl.read(id);
    }

    @Transactional
    public void deleteOrderById(int orderId) {
        ordersDAOImpl.delete(ordersDAOImpl.read(orderId));
    }

    @Transactional
    public void updateOrderInfo(int orderId, int tableNumber, int waiterId) {
        Orders order = ordersDAOImpl.read(orderId);
        order.setTableNumber(tableNumber);
        order.setEmployee(employeeService.getEmployeeByID(waiterId));
    }

    @Transactional
    public void addTheDishToOrder(int orderId, int dishId) {

        List<Dish> dishes = ordersDAOImpl.read(orderId).getDishes();

        dishes.add(dishesDAOImpl.read(dishId));

        ordersDAOImpl.read(orderId).setListOfDishes(dishes);
    }

    @Transactional
    public List<Orders> getOrdersByWaiter(Employee waiter) {
        return ordersDAOImpl.getOrdersByWaiter(waiter);
    }

    @Transactional
    public List<Orders> getOrderByDate(String date) {
        return ordersDAOImpl.getOrdersByDate(date);
    }

    public String enoughIngredients(ArrayList selectedDishes) {

        boolean result = true;

        StringBuffer message = new StringBuffer();

        HashMap<Integer, Integer> amountOfIngredientsForOrder = amountOfEachIngredientId(selectedDishes);
        System.out.println(amountOfIngredientsForOrder.toString());

        for (Integer i : amountOfIngredientsForOrder.keySet()) {
            int ingredientId = i;
            Storage ingredientInStorage = storageService.getById(ingredientId);

            if (ingredientInStorage.getAmount() - amountOfIngredientsForOrder.get(ingredientId) < 0) {
                result = false;
                message.append("\nNot enough " + storageService.getById(ingredientId).getIngredient().getName() + "!");
            }
        }

        if (result) {
            message.append("successful added!");
        }

        return message.toString();
    }

    @Transactional
    public void decreaseIngredients(ArrayList selectedDishes) {

        HashMap<Integer, Integer> amountOfIngredientsForOrder = amountOfEachIngredientId(selectedDishes);

        for (Integer ingredientId : amountOfIngredientsForOrder.keySet()) {

            Storage ingredientInStorage = storageService.getById(ingredientId);
            int amountOfIngredientAfterDecrease = ingredientInStorage.getAmount() - amountOfIngredientsForOrder.get(ingredientId);

            ingredientInStorage.setAmount(amountOfIngredientAfterDecrease);
        }

    }

    private HashMap<Integer, Integer> amountOfEachIngredientId(ArrayList selectedDishes) {

        ArrayList<Dish> dishesToOrder = new ArrayList<>();

        for (int i = 0; i < selectedDishes.size(); i++) {
            int id = Integer.parseInt(selectedDishes.get(i).toString());
            dishesToOrder.add(dishesDAOImpl.read(id));
        }

        ArrayList<Ingredient> ingredientsToOrder = new ArrayList<>();

        for (int i = 0; i < dishesToOrder.size(); i++) {

            Dish dish = dishesToOrder.get(i);

            List<Ingredient> ingredientsToDish = dish.getIngredients();

            for (int j = 0; j < ingredientsToDish.size(); j++) {
                ingredientsToOrder.add(ingredientsToDish.get(j));
            }
        }

        HashMap<Integer, Integer> amountOfIngredientsForOrder = new HashMap();

        for (int i = 0; i < ingredientsToOrder.size(); i++) {

            int id = ingredientsToOrder.get(i).getID();

            if (amountOfIngredientsForOrder.keySet().contains(id)) {
                int amount = amountOfIngredientsForOrder.get(id);
                amountOfIngredientsForOrder.put(id, amount + 1);
            } else {
                amountOfIngredientsForOrder.put(id, 1);
            }
        }
        return amountOfIngredientsForOrder;
    }

    @Transactional
    public void deleteDishFromOrder(int orderId, int dishId) {

        Orders orders = ordersDAOImpl.read(orderId);
        Dish dish = dishesDAOImpl.read(dishId);

        List<Dish> dishes = orders.getDishes();
        dishes.remove(dish);

        orders.setListOfDishes(dishes);

        for (Ingredient ingredient : dish.getIngredients()) {
            Storage ingredientInStorage = storageService.getById(ingredient.getID());
            int newAmount = ingredientInStorage.getAmount() + 1;
            ingredientInStorage.setAmount(newAmount);
        }
    }


    @Transactional
    public boolean IsThereNoOrdersWithThisEmployee(Employee waiter) {

        boolean result = false;

        List<Orders> list = ordersDAOImpl.getOrdersByWaiter(waiter);

        if (list.isEmpty()) result = true;

        return result;
    }

    @Transactional
    public void closeOrder(int orderID) {
        getOrderById(orderID).setAccess(false);
    }

    @Transactional
    public List<Orders> getOrdersByTableNumber(int tableNumber) {
        return ordersDAOImpl.getOrdersByTableNumber(tableNumber);
    }

    @Transactional
    public void creatingOrder(int waiterId, int tableNumber, ArrayList selectedDishes) {

        Orders order = new Orders();

        order.setEmployee(employeeService.getEmployeeByID(waiterId));
        order.setTableNumber(tableNumber);
        order.setDate(LocalDateTime.now().format(ofLocalizedDateTime(FormatStyle.SHORT)));
        order.setAccess(true);

        List<Dish> dishes = new ArrayList<>();

        for (int i = 0; i < selectedDishes.size(); i++) {
            Dish dish = dishesDAOImpl.read(Integer.parseInt(selectedDishes.get(i).toString()));
            dishes.add(dish);
        }

        decreaseIngredients(selectedDishes);

        order.setListOfDishes(dishes);

        ordersDAOImpl.create(order);
    }

    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setOrdersDAOImpl(OrdersDAOImpl ordersDAOImpl) {
        this.ordersDAOImpl = ordersDAOImpl;
    }

    public void setDishesDAOImpl(DishesDAOImpl dishesDAOImpl) {
        this.dishesDAOImpl = dishesDAOImpl;
    }
}
