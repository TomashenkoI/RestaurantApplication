package ua.goit.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.*;
import ua.goit.java.dao.model.*;

import java.util.ArrayList;
import java.util.List;

public class DishService {

    private DishesDAO dishesDao;
    private ListOfIngredientsDAO listOfIngredientsDAO;
    private IngredientsDAO ingredientsDAO;
    private OrdersDAO ordersDAO;

    public List<Dish> getDishes(){
        return dishesDao.findAll();
    }

    @Transactional
    public void updateDishInfo(int id, Dish dishWithNewInformation) {
        dishesDao.updateDish(id, dishWithNewInformation);
    }

    @Transactional
    public List<Ingredient> getIngredientsToThisDish(int dishId) {

        List<ListOfIngredients> listOfIngredients = listOfIngredientsDAO.findByDishId(dishId);

        List<Ingredient> ingredients = new ArrayList<>();
        for (ListOfIngredients ingredientsToDish : listOfIngredients) {
            ingredients.add(ingredientsDAO.getById(ingredientsToDish.getIngredientID()));
        }

        return ingredients;
    }

    @Transactional
    public List<Ingredient> setListOfIngredients(List listOfIngredientsForTheDish) {

        ArrayList<Ingredient> ingredients = new ArrayList<>();

        for (int i = 0; i < listOfIngredientsForTheDish.size(); i++) {
            int id = Integer.parseInt(listOfIngredientsForTheDish.get(i).toString());
            Ingredient ingredient = ingredientsDAO.getById(id);
            ingredients.add(ingredient);
        }

        return ingredients;
    }

    @Transactional
    public Dish getDishByID(int dishId) {
        return dishesDao.findById(dishId);
    }


    @Transactional
    public void delete(int dishId) {
        listOfIngredientsDAO.removeById(dishId);
        dishesDao.remove(dishesDao.findById(dishId));
    }

    public List<Dish> getDishByName(String dishName) {
        return dishesDao.findByName(dishName);
    }

    @Transactional
    public void addNewDish(Dish dish) {
        dishesDao.save(dish);
    }

    @Transactional
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

    @Transactional
    public boolean canDishBeDeleted(int dishID) {

        boolean result = true;

        List<Orders> list = ordersDAO.findAll();

        for (Orders orders : list) {
            for (Dish dishFromOrder : orders.getDishes()) {
                if (dishFromOrder.getID() == dishID) {
                    result = false;
                }
            }

        }
        return result;
    }

    @Transactional
    public void saveNewDish(String name, String category, double price, int weight, List listOfIngredients) {

        Dish dish = new Dish();

        dish.setName(name);
        dish.setPrice(price);
        dish.setWeight(weight);
        dish.setIngredients(setListOfIngredients(listOfIngredients));
        setDishCategory(category, dish);

        dishesDao.save(dish);
    }

    public void setDishesDao(DishesDAO dishesDao) {
        this.dishesDao = dishesDao;
    }

    @Autowired
    public void setListOfIngredientsDAO(ListOfIngredientsDAO listOfIngredientsDAO) {
        this.listOfIngredientsDAO = listOfIngredientsDAO;
    }

    public void setOrdersDAO(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    @Autowired
    public void setIngredientsDAO(IngredientsDAO ingredientsDAO) {
        this.ingredientsDAO = ingredientsDAO;
    }
}


