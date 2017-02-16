package ua.goit.java.dao;

import ua.goit.java.dao.model.Dish;

import java.util.List;

public interface DishesDAO extends GenericDAO<Dish, Integer> {

    List<Dish> findByName(String name);

}
