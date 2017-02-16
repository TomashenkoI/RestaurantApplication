package ua.goit.java.dao.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int ID;

    @Column(name = "dishcategory")
    @Enumerated(EnumType.STRING)
    private DishCategory dishCategory;

//    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ingredients_to_dish",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "weight")
    private int weight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;
        Dish dish = (Dish) o;
        return ID == dish.ID &&
                Double.compare(dish.price, price) == 0 &&
                weight == dish.weight &&
                dishCategory == dish.dishCategory &&
                Objects.equals(ingredients, dish.ingredients) &&
                Objects.equals(name, dish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, dishCategory, ingredients, name, price, weight);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
