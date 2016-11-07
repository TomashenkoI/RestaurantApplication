package ua.goit.java.dao.model;

import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
@Entity
@Table(name = "cooked_dishes")
public class CookedDish {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int ID;

    @Column(name = "dish_id")
    private int dishId;

    @ManyToOne()
    @JoinColumn(name = "cook_id")
    private Employee employee;

    @Column(name = "date")
    private String date;

    @Column(name = "order_id")
    private int orderId;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "CookedDish{" +
                "ID=" + ID +
                ", dishId=" + dishId +
                ", employee=" + employee +
                ", date='" + date + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
