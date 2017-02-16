package ua.goit.java.dao.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Waiter extends Employee {

    @OneToMany()
    @JoinColumn(name = "id")
    private List<Orders> orders;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

}
