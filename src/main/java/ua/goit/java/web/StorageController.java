package ua.goit.java.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.dao.model.Ingredient;
import ua.goit.java.dao.model.Storage;
import ua.goit.java.service.StorageService;

@Controller
public class StorageController {

    private StorageService storageService;

    @RequestMapping(value = "/admin/storage", method = RequestMethod.GET)
    public ModelAndView allIngredients() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("storage", storageService.getAll());

        modelAndView.setViewName("admin/storage");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/storage", method = RequestMethod.POST)
    public ModelAndView addNewIngredient(@RequestParam("ingredientName") String name,
                                         @RequestParam("ingredientAmount") int amount) {

        storageService.addNewIngredient(new Ingredient(name), amount);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("storage", storageService.getAll());

        modelAndView.setViewName("admin/storage");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/findIngredientsByName")
    public ModelAndView findIngredientsByName(@RequestParam String ingredientName) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("ingredientName", ingredientName);
        modelAndView.addObject("storage", storageService.getByName(ingredientName));

        modelAndView.setViewName("admin/storage");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/storage/ingredientId={ingredientId}", method = RequestMethod.GET)
    public ModelAndView getIngredient(@PathVariable int ingredientId) {

        ModelAndView modelAndView = new ModelAndView();

        Storage ingredient = storageService.getById(ingredientId);

        modelAndView.addObject("ingredient", ingredient);

        modelAndView.setViewName("admin/ingredient");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete_ingredientId={ingredientId}", method = RequestMethod.GET)
    public ModelAndView deleteIngredient(@PathVariable int ingredientId) {

        ModelAndView modelAndView = new ModelAndView();

        boolean ingredientIsNotPresentInDishes = storageService.ingredientCanBeDeleted(ingredientId);

        if (ingredientIsNotPresentInDishes) {

            storageService.delete(storageService.getById(ingredientId));

            modelAndView.addObject("storage", storageService.getAll());

            modelAndView.setViewName("admin/storage");

        } else {

            modelAndView.addObject("needAlert", true);
            modelAndView.addObject("ingredient", storageService.getById(ingredientId));

            modelAndView.setViewName("admin/ingredient");

        }

        return modelAndView;
    }

    @RequestMapping(value = "/admin/updated_ingredientId={ingredientId}", method = RequestMethod.POST)
    public ModelAndView updatedIngredient(@PathVariable int ingredientId,
                                          @RequestParam("ingredientName") String ingredientName,
                                          @RequestParam("ingredientAmount") int ingredientAmount) {

        ModelAndView modelAndView = new ModelAndView();

        storageService.updateStorage(ingredientId, ingredientName, ingredientAmount);

        modelAndView.addObject("storage", storageService.getAll());

        modelAndView.setViewName("admin/storage");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/update_ingredientId={ingredientId}", method = RequestMethod.GET)
    public ModelAndView updatingInfo(@PathVariable int ingredientId) {

        ModelAndView modelAndView = new ModelAndView();
        boolean doesIfAlreadyExist = true;

        modelAndView.addObject("doesItAlreadyExist", doesIfAlreadyExist);

        modelAndView.addObject("ingredient", storageService.getById(ingredientId));

        modelAndView.setViewName("admin/newIngredient");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/newIngredient")
    public ModelAndView newIngredient() {

        ModelAndView modelAndView = new ModelAndView();

        boolean doesIfAlreadyExist = false;

        modelAndView.addObject("doesItAlreadyExist", doesIfAlreadyExist);

        modelAndView.setViewName("admin/newIngredient");

        return modelAndView;
    }

    @Autowired
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }
}
