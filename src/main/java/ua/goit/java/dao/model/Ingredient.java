package ua.goit.java.dao.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int ID;

    @Column(name = "name")
    private String name;

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

    public Ingredient(String name) {
        this.name = name;
    }

    protected Ingredient() {
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}

