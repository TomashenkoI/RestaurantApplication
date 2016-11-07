package ua.goit.java.dao.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int ingredient_ID;

    @OneToOne
    @JoinColumn(name = "INGREDIENT_ID")
    private Ingredient ingredient;

    @Column(name = "amount")
    private int amount;

    public int getIngredient_ID() {
        return ingredient_ID;
    }

    public void setIngredient_ID(int ingredient_ID) {
        this.ingredient_ID = ingredient_ID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    private Storage() {
    }

    public Storage(Ingredient ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Storage{" +
                ", ingredient_ID=" + ingredient_ID +
                ", amount=" + amount +
                '}';
    }
}
