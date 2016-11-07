package ua.goit.java.dao.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int ID;

    @ManyToOne
    @JoinColumn(name = "waiter_id")
    private Employee employee;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "dish_to_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> listOfDishes;

    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "date")
    private String date;

    @Column(name = "access")
    private boolean access;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Dish> getDishes() {
        return listOfDishes;
    }

    public void setListOfDishes(List<Dish> dishes) {
        this.listOfDishes = dishes;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public boolean isAccess() {
        return access;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ID=" + ID +
                ", employee=" + employee +
                ", listOfDishesID=" + listOfDishes +
                ", tableNumber=" + tableNumber +
                ", date='" + date + '\'' +
                '}';
    }
}
