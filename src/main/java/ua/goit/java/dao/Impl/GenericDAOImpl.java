package ua.goit.java.dao.Impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.GenericDAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> entityClass;

    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        entityClass = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public void create(T entityObject) {
        sessionFactory.getCurrentSession().save(entityObject);
    }

    @Override
    @Transactional
    public T read(ID id) {
        T t = sessionFactory.getCurrentSession().find(entityClass, id);
        return t;
    }

    @Override
    public void update(T entityObject) {
        sessionFactory.getCurrentSession().update(entityObject);
    }

    @Override
    public void delete(T entityObject) {
        sessionFactory.getCurrentSession().delete(entityObject);
    }

    @Override
    @Transactional
    public List<T> findAll() {
        List<T> list = sessionFactory.getCurrentSession().createCriteria(entityClass).list();
        return list;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
