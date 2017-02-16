package ua.goit.java.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.dao.model.Dish;
import ua.goit.java.dao.model.Ingredient;
import ua.goit.java.service.DishService;
import ua.goit.java.service.IngredientService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DishController{

    private DishService dishService;
    private IngredientService ingredientService;

    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    public ModelAndView dishes(){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("needToAddTheDishToOrder", false);
        modelAndView.addObject("dishes", dishService.getDishes());

        modelAndView.setViewName("dishes");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/dishes", method = RequestMethod.GET)
    public ModelAndView adminDishes(){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("needToAddTheDishToOrder", false);
        modelAndView.addObject("dishes", dishService.getDishes());

        modelAndView.setViewName("admin/dishes");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/findDishesByName", method = RequestMethod.GET)
    public ModelAndView adminFindDishesByName(@RequestParam("dishName") String dishName) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("dishName", dishName);
        modelAndView.addObject("dishes", dishService.getDishByName(dishName));
        modelAndView.addObject("needToAddTheDishToOrder", false);

        modelAndView.setViewName("admin/dishes");

        return modelAndView;
    }

    @RequestMapping(value = "/findDishesByName", method = RequestMethod.GET)
    public ModelAndView findDishesByName(@RequestParam("dishName") String dishName) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("dishName", dishName);
        modelAndView.addObject("dishes", dishService.getDishByName(dishName));

        modelAndView.setViewName("dishes");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/dishes", method = RequestMethod.POST)
    public ModelAndView addNewDish(@RequestParam("selectedIngredients") ArrayList selectedIngredients,
                                   @RequestParam("dishName") String name,
                                   @RequestParam("dishCategory") String category,
                                   @RequestParam("dishPrice") int price,
                                   @RequestParam("dishWeight") int weight
                                   )
    {

        ModelAndView modelAndView = new ModelAndView();

        dishService.saveNewDish(name, category, price, weight, selectedIngredients);

        modelAndView.addObject("needToAddTheDishToOrder", false);
        modelAndView.addObject("dishes", dishService.getDishes());

        modelAndView.setViewName("admin/dishes");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/dishes/dishId={dishId}", method = RequestMethod.GET)
    public ModelAndView adminDish(@PathVariable int dishId){

        List<Ingredient> ingredientsToDish = dishService.getIngredientsToThisDish(dishId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dish", dishService.getDishByID(dishId));
        modelAndView.addObject("ingredientsToDish", ingredientsToDish);
        setPictureForDish(dishId, modelAndView);

        modelAndView.setViewName("admin/dish");


        return modelAndView;
    }

    @RequestMapping(value = "/dishes/dishId={dishId}", method = RequestMethod.GET)
    public ModelAndView dish(@PathVariable int dishId){

        List<Ingredient> ingredientsToDish = dishService.getIngredientsToThisDish(dishId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dish", dishService.getDishByID(dishId));
        modelAndView.addObject("ingredientsToDish", ingredientsToDish);
        setPictureForDish(dishId, modelAndView);

        modelAndView.setViewName("dish");


        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete_dishId={dishID}", method = RequestMethod.GET)
    public ModelAndView deleteDish(@PathVariable int dishID) {

        ModelAndView modelAndView = new ModelAndView();

        boolean theDishCanBeDeleted = dishService.canDishBeDeleted(dishID);

        if (theDishCanBeDeleted) {
            dishService.delete(dishID);

            modelAndView.addObject("needToAddTheDishToOrder", false);
            modelAndView.addObject("dishes", dishService.getDishes());

            modelAndView.setViewName("admin/dishes");

        } else {

            modelAndView.addObject("dish", dishService.getDishByID(dishID));
            modelAndView.addObject("ingredientsToDish", dishService.getDishByID(dishID).getIngredients());
            modelAndView.addObject("needAlert", true);

            modelAndView.setViewName("admin/dish");
            setPictureForDish(dishID, modelAndView);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/admin/newDish", method = RequestMethod.GET)
    public ModelAndView addNewDish() {

        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        boolean doesItAlreadyExist = false;

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("listOfIncludedIngredients", new ArrayList<>());
        modelAndView.addObject("doesItAlreadyExist", doesItAlreadyExist);
        modelAndView.addObject("ingredients", ingredients);

        modelAndView.setViewName("admin/newDish");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/update_dishId={dishID}", method = RequestMethod.GET)
    public ModelAndView updateDish(@PathVariable int dishID) {

        ModelAndView modelAndView = new ModelAndView();

        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        boolean doesItAlreadyExist = true;

        modelAndView.addObject("doesItAlreadyExist", doesItAlreadyExist);
        modelAndView.addObject("ingredients", ingredients);

        modelAndView.addObject("dish", dishService.getDishByID(dishID));

        modelAndView.setViewName("admin/newDish");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/updated_DishId={dishId}", method = RequestMethod.POST)
    public ModelAndView updateExistingDish(@PathVariable int dishId,
                                           @RequestParam("selectedIngredients") ArrayList selectedIngredients,
                                           @RequestParam("dishName") String dishName,
                                           @RequestParam("dishCategory") String category,
                                           @RequestParam("dishPrice") double price,
                                           @RequestParam("dishWeight") int weight)
    {

        ModelAndView modelAndView = new ModelAndView();

        dishService.updateDishInfo(dishId, selectedIngredients, dishName, category, price, weight);

        Dish dish = dishService.getDishByID(dishId);

        setPictureForDish(dishId, modelAndView);
        modelAndView.addObject("ingredientsToDish", dish.getIngredients());
        modelAndView.addObject("dish", dishService.getDishByID(dishId));

        modelAndView.setViewName("admin/dish");

        return modelAndView;
    }

    private void setPictureForDish(@PathVariable int dishId, ModelAndView modelAndView) {
        String path = "D:\\edu\\IdeaProjects\\Restaurant_MVC\\src\\main\\webapp\\resources\\images\\dishes\\id"+dishId+".jpg";
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {
            path = "/images/dishes/id"+dishId+".jpg";
            modelAndView.addObject("picturePath", path);
        } else {
            path = "/images/dishes/default.jpg";
            modelAndView.addObject("picturePath", path);
        }
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @Autowired
    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
}
