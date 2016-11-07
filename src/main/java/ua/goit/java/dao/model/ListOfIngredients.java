package ua.goit.java.dao.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "ingredients_to_dish")
public class ListOfIngredients implements Serializable {

    @Id
    @Column(name = "ingredient_id")
    private int ingredientID;

    @Id
    @Column(name = "dish_id")
    private int dishID;

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    @Override
    public String toString() {
        return "ListOfIngredients{" +
                "ingredientID=" + ingredientID +
                ", dishID=" + dishID +
                '}';
    }
}