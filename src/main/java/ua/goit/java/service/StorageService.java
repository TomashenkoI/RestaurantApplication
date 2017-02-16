package ua.goit.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.DishesDAO;
import ua.goit.java.dao.Impl.DishesDAOImpl;
import ua.goit.java.dao.Impl.IngredientsDAOImpl;
import ua.goit.java.dao.Impl.StorageDAOImpl;
import ua.goit.java.dao.IngredientsDAO;
import ua.goit.java.dao.StorageDAO;
import ua.goit.java.dao.model.Dish;
import ua.goit.java.dao.model.Ingredient;
import ua.goit.java.dao.model.Storage;

import java.util.List;

@Service
public class StorageService {

    @Autowired
    private StorageDAO storageDAO;

    @Autowired
    private IngredientsDAO ingredientsDAO;

    @Autowired
    private DishesDAO dishesDAO;

    public List<Storage> getAll() {
        return storageDAO.findAll();
    }

    @Transactional
    public void addNewIngredient(Ingredient ingredient, int amount) {

        ingredientsDAO.create(ingredient);
        storageDAO.create(new Storage(ingredient, amount));
    }

    @Transactional
    public void updateStorage(int storageId, String name, int amount) {

        Storage storage = getById(storageId);
        storage.setAmount(amount);
        storage.getIngredient().setName(name);

        storageDAO.update(storage);
    }

    @Transactional
    public boolean ingredientCanBeDeleted(int ingredientId) {

        boolean result = true;

        List<Dish> list = dishesDAO.findAll();

        for (Dish d : list) {
            for (Ingredient ing : d.getIngredients()) {
                if (ing.getID() == ingredientId) {
                    result = false;
                }
            }

        }

        return result;
    }

    @Transactional
    public List<Storage> getByName(String ingredientName) {
        return storageDAO.findByIngredientName(ingredientName);
    }

    @Transactional
    public Storage getById(int ingredientId) {
        return storageDAO.read(ingredientId);
    }

    @Transactional
    public void delete(Storage storage) {
        storageDAO.delete(storage);
        ingredientsDAO.delete(storage.getIngredient());
    }

    public void setDishesDAO(DishesDAOImpl dishesDAO) {
        this.dishesDAO = dishesDAO;
    }

    public void setStorageDAO(StorageDAOImpl storageDAO) {
        this.storageDAO = storageDAO;
    }

    public void setIngredientsDAO(IngredientsDAOImpl ingredientsDAO) {
        this.ingredientsDAO = ingredientsDAO;
    }
}
