package ua.goit.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.java.dao.DishesDAO;
import ua.goit.java.dao.Impl.DishesDAOImpl;
import ua.goit.java.dao.Impl.OrdersDAOImpl;
import ua.goit.java.dao.model.Dish;
import ua.goit.java.dao.model.DishCategory;
import ua.goit.java.dao.model.Ingredient;
import ua.goit.java.dao.model.Orders;

import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishesDAO dishesDao;

    @Autowired
    private OrdersDAOImpl ordersDAOImpl;

    public List<Dish> getDishes(){
        return dishesDao.findAll();
    }

    public List<Ingredient> getIngredientsToThisDish(int dishId) {
        return dishesDao.read(dishId).getIngredients();
    }

    public void updateDishInfo(int id, List listOfIngredientsForTheDish, String dishName, String dishCategory,
                               double price, int weight) {

        Dish dish = dishesDao.read(id);

        dish.setIngredients(listOfIngredientsForTheDish);
        setDishCategory(dishCategory, dish);
        dish.setName(dishName);
        dish.setPrice(price);
        dish.setWeight(weight);

        dishesDao.update(dish);
    }

    public Dish getDishByID(int dishId) {
        return dishesDao.read(dishId);
    }


    public void delete(int dishId) {
        dishesDao.delete(dishesDao.read(dishId));
    }

    public List<Dish> getDishByName(String dishName) {
        return dishesDao.findByName(dishName);
    }


    public void setDishCategory(String category, Dish dish) {

        if (category.equals("Soup")) {
            dish.setDishCategory(DishCategory.SOUP);
        } else if (category.equals("Salad")) {
            dish.setDishCategory(DishCategory.SALAD);
        } else if (category.equals("Dessert")) {
            dish.setDishCategory(DishCategory.DESSERT);
        } else if (category.equals("Garnish")) {
            dish.setDishCategory(DishCategory.GARNISH);
        } else if (category.equals("Main course")) {
            dish.setDishCategory(DishCategory.MAIN_COURSE);
        }
    }

    public boolean canDishBeDeleted(int dishID) {

        boolean result = true;

        List<Orders> list = ordersDAOImpl.findAll();

        for (Orders orders : list) {
            for (Dish dishFromOrder : orders.getDishes()) {
                if (dishFromOrder.getID() == dishID) {
                    result = false;
                }
            }

        }
        return result;
    }

    public void saveNewDish(String name, String category, double price, int weight, List listOfIngredients) {

        Dish dish = new Dish();

        dish.setName(name);
        dish.setPrice(price);
        dish.setWeight(weight);
        dish.setIngredients(listOfIngredients);
        setDishCategory(category, dish);

        dishesDao.create(dish);
    }

    public void setDishesDao(DishesDAOImpl dishesDao) {
        this.dishesDao = dishesDao;
    }

    public void setOrdersDAOImpl(OrdersDAOImpl ordersDAOImpl) {
        this.ordersDAOImpl = ordersDAOImpl;
    }
}


