package ua.goit.java.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.model.Ingredient;
import ua.goit.java.dao.model.Storage;

import java.util.List;

public class StorageDAO {

    private IngredientsDAO ingredientsDAO;
    private SessionFactory sessionFactory;


    public void save(Storage storage) {
        Session session = sessionFactory.getCurrentSession();
        session.save(storage);
    }

    public void remove(Storage storage) {
        sessionFactory.getCurrentSession().delete(storage);
    }

    @Transactional
    public Storage findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from Storage s where s.ingredient_ID = :id");
        query.setParameter("id", id);

        return (Storage) query.uniqueResult();
    }

    @Transactional
    public void updateStorageInfo(int storageId, int amount) {
        Session session = sessionFactory.getCurrentSession();
        Storage storage = session.get(Storage.class, storageId);

        storage.setAmount(amount);

        session.update(storage);
    }

    @Transactional
    public List<Storage> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select s from Storage s").list();
    }

    public List<Storage> getIngredientsByName(String ingredientName) {

        Ingredient ingredient = ingredientsDAO.getIngredientByName(ingredientName);

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from Storage s where s.ingredient = :ingredient");
        query.setParameter("ingredient", "%"+ingredient+"%");

        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setIngredientsDAO(IngredientsDAO ingredientsDAO) {
        this.ingredientsDAO = ingredientsDAO;
    }
}
