package ua.goit.java.service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.DishesDAO;
import ua.goit.java.dao.IngredientsDAO;
import ua.goit.java.dao.StorageDAO;
import ua.goit.java.dao.model.Dish;
import ua.goit.java.dao.model.Ingredient;
import ua.goit.java.dao.model.Storage;

import java.util.List;

public class StorageService {

    private StorageDAO storageDAO;
    private IngredientsDAO ingredientsDAO;
    private DishesDAO dishesDAO;

    public List<Storage> getAll() {
        return storageDAO.findAll();
    }

    @Transactional
    public void addNewIngredient(Ingredient ingredient, int amount) {

        ingredientsDAO.save(ingredient);
        storageDAO.save(new Storage(ingredient, amount));
    }

    @Transactional
    public void updateStorage(int storageId, String name, int amount) {

        Storage storage = getById(storageId);

        ingredientsDAO.updateIngredient(storage.getIngredient_ID(), name);
        storageDAO.updateStorageInfo(storageId, amount);
    }

    @Transactional
    public boolean ingredientCanBeDeleted(int ingredientId) {

        boolean result = true;

        List<Dish> list = dishesDAO.findAll();

        Ingredient i = ingredientsDAO.getById(ingredientId);

        for (Dish d : list) {
            for (Ingredient ing : d.getIngredients()) {
                if (ing.getID() == ingredientId) result = false;
            }

        }

        return result;
    }

    @Transactional
    public List<Storage> getByName(String ingredientName) {
        return storageDAO.getIngredientsByName(ingredientName);
    }

    @Transactional
    public Storage getById(int ingredientId) {
        return storageDAO.findById(ingredientId);
    }

    @Transactional
    public void delete(Storage storage) {
        storageDAO.remove(storage);
        ingredientsDAO.remove(storage.getIngredient());
    }

    public void setDishesDAO(DishesDAO dishesDAO) {
        this.dishesDAO = dishesDAO;
    }

    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    public void setIngredientsDAO(IngredientsDAO ingredientsDAO) {
        this.ingredientsDAO = ingredientsDAO;
    }
}
