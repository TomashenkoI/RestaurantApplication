package ua.goit.java.dao;

import java.util.List;

public interface GenericDAO<T, PK> {

    void create(T entityObject);

    T read(PK id);

    void update(T entityObject);

    void delete(T entityObject);

    List<T> findAll();

}
