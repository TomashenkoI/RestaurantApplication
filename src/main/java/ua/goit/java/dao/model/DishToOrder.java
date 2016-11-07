package ua.goit.java.dao.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "dish_to_order")
public class DishToOrder implements Serializable{

    @Id
    @Column(name = "dish_id")
    private int dishId;

    @Id
    @Column(name = "order_Id")
    private int orderId;

    @Override
    public String toString() {
        return "DishToOrder{" +
                "ID=" + dishId +
                ", dishID_N1=" + dishId +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
}
