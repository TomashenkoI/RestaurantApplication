package ua.goit.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.java.dao.Impl.IngredientsDAOImpl;
import ua.goit.java.dao.IngredientsDAO;
import ua.goit.java.dao.model.Ingredient;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientsDAO ingredientsDAO;

    public List<Ingredient> getAllIngredients() {
        return ingredientsDAO.findAll();
    }

    public void setIngredientsDAOImpl(IngredientsDAOImpl ingredientsDAOImpl) {
        this.ingredientsDAO = ingredientsDAOImpl;
    }

}
