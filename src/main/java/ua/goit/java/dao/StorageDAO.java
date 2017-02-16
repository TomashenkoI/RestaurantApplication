package ua.goit.java.dao;

import ua.goit.java.dao.model.Storage;

import java.util.List;

public interface StorageDAO extends GenericDAO<Storage, Integer> {

    List<Storage> findByName(String ingredientName);

}
