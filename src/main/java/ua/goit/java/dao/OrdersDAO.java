package ua.goit.java.dao;

import ua.goit.java.dao.model.Employee;
import ua.goit.java.dao.model.Orders;

import java.util.List;

public interface OrdersDAO extends GenericDAO<Orders, Integer> {

    List<Orders> getOrdersByWaiter(Employee waiter);

    List<Orders> getOrdersByDate(String date);

    List<Orders> getOrdersByTableNumber(int tableNumber);

}
