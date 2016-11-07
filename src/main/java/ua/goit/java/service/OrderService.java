package ua.goit.java.service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.*;
import ua.goit.java.dao.model.*;

import java.util.*;

public class OrderService {

    private OrdersDAO ordersDAO;
    private DishesDAO dishesDAO;
    private DishToOrderDAO dishToOrderDAO;
    private EmployeeService employeeService;
    private StorageService storageService;
    private Requests requests = new Requests();

    public List<Dish> allDishes() {
        return dishesDAO.findAll();
    }

    public List<Orders> getAllOrders() {
        return ordersDAO.findAll();
    }

    @Transactional
    public Orders getOrderById(int id) {
        return ordersDAO.findById(id);
    }

    @Transactional
    public void deleteOrderById(int orderId) {
        dishToOrderDAO.removeById(orderId);
        ordersDAO.remove(ordersDAO.findById(orderId));
    }

    @Transactional
    public void updateOrderInfo(int orderId, int tableNumber, int waiterId) {
        Orders order = ordersDAO.findById(orderId);
        order.setTableNumber(tableNumber);
        order.setEmployee(employeeService.getEmployeeByID(waiterId));
    }

    @Transactional
    public void addTheDishToOrder(int orderId, int dishId) {

        List<Dish> dishes = ordersDAO.findById(orderId).getDishes();

        dishes.add(dishesDAO.findById(dishId));

        ordersDAO.findById(orderId).setListOfDishes(dishes);
    }

    @Transactional
    public List<Orders> getOrdersByWaiter(Employee employee) {
        return ordersDAO.getOrdersByWaiter(employee);
    }

    @Transactional
    public List<Orders> getOrderByDate(String date) {
        return ordersDAO.getOrdersByDate(date);
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
            dishesToOrder.add(dishesDAO.findById(id));
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

        Orders orders = ordersDAO.findById(orderId);
        Dish dish = dishesDAO.findById(dishId);

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
    public boolean IsThereNoOrdersWithThisEmployee(Employee employee) {

        boolean result = false;

        List<Orders> list = ordersDAO.getOrdersByWaiter(employee);

        if (list.isEmpty()) result = true;

        return result;
    }

    @Transactional
    public void closeOrder(int orderID) {
        getOrderById(orderID).setAccess(false);
    }

    @Transactional
    public List<Orders> getOrdersByTableNumber(int tableNumber) {
        return ordersDAO.getOrdersByTableNumber(tableNumber);
    }

    @Transactional
    public void creatingOrder(int waiterId, int tableNumber, ArrayList selectedDishes) {

        Orders order = new Orders();

        order.setEmployee(employeeService.getEmployeeByID(waiterId));
        order.setTableNumber(tableNumber);
        order.setDate(requests.getCurrentTime());
        order.setAccess(true);

        List<Dish> dishes = new ArrayList<>();

        for (int i = 0; i < selectedDishes.size(); i++) {
            Dish dish = dishesDAO.findById(Integer.parseInt(selectedDishes.get(i).toString()));
            dishes.add(dish);
        }

        decreaseIngredients(selectedDishes);

        order.setListOfDishes(dishes);

        ordersDAO.save(order);
    }

    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setDishToOrderDAO(DishToOrderDAO dishToOrderDAO) {
        this.dishToOrderDAO = dishToOrderDAO;
    }

    public void setOrdersDAO(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    public void setDishesDAO(DishesDAO dishesDAO) {
        this.dishesDAO = dishesDAO;
    }
}
