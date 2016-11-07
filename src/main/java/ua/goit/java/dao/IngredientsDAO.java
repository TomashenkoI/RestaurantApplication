package ua.goit.java.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.model.Ingredient;

import java.util.List;
import java.util.Scanner;

public class IngredientsDAO {

    private SessionFactory sessionFactory;

    public void save(Ingredient ingredient) {
        Session session = sessionFactory.getCurrentSession();
        session.save(ingredient);
    }

    public void remove(Ingredient ingredient) {
        sessionFactory.getCurrentSession().delete(ingredient);
    }

    @Transactional
    public List<Ingredient> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select i from Ingredient i").list();
    }

    public Ingredient findByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Ingredient i where i.name = :name");
        query.setParameter("name", name);

        return (Ingredient) query.uniqueResult();
    }

    @Transactional
    public void updateIngredient(int ingredientId, String name) {
        Session session = sessionFactory.getCurrentSession();
        Ingredient ingredient = session.get(Ingredient.class, ingredientId);

        ingredient.setName(name);

        session.update(ingredient);
    }

    public void enteringInformation(Ingredient ingredient) {

        System.out.println("Введите название ингридиента");
        String name = new Scanner(System.in).nextLine();
        ingredient.setName(name);
        save(ingredient);
    }


    public Ingredient getById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Ingredient i where i.ID = :id");
        query.setParameter("id", id);

        return (Ingredient) query.uniqueResult();
    }

    public List<Ingredient> getIngredientsByName(String ingredientName) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Ingredient i where i.name like :name");
        query.setParameter("name", "%"+ingredientName+"%");

        return query.list();
    }

    public int getIngredientIdByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i.ID from Ingredient i where i.name = :name");
        query.setParameter("name", name);

        return (int) query.uniqueResult();

    }

    @Transactional
    public int getMaxId() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session
                .createCriteria(Ingredient.class)
                .setProjection(Projections.max("id"));
        return (Integer)criteria.uniqueResult();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Ingredient getIngredientByName(String ingredientName) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Ingredient i where i.name like :name");
        query.setParameter("name", "%"+ingredientName+"%");

        return (Ingredient) query.uniqueResult();
    }
}
