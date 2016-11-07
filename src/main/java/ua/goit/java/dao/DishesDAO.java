package ua.goit.java.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.model.Dish;

import java.util.List;

public class DishesDAO {

    private SessionFactory sessionFactory;


    public Dish getDishByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d From Dish d where d.name = :name");
        query.setParameter("name", name);

        return (Dish) query.uniqueResult();
    }

    @Transactional
    public void save(Dish dish) {
        Session session = sessionFactory.getCurrentSession();
        session.save(dish);
    }

    @Transactional
    public void remove(Dish dish){
        sessionFactory.getCurrentSession().delete(dish);
    }

    @Transactional
    public List<Dish> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select d from Dish d").list();
    }

    @Transactional
    public List<Dish> findByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Dish d where d.name like :name");
        query.setParameter("name", "%"+name+"%");

        return query.list();

    }

    @Transactional
    public void updateDish(int dishId, Dish newDish){
        Session session = sessionFactory.getCurrentSession();
        Dish dish = session.get(Dish.class, dishId);

        if (newDish.getName() != null) {
            dish.setName(newDish.getName());
        }
        if (newDish.getDishCategory() != null) {
            dish.setDishCategory(newDish.getDishCategory());
        }
        if ((Double)newDish.getPrice() != null) {
            dish.setPrice(newDish.getPrice());
        }
        if ((Integer)newDish.getWeight() != null) {
            dish.setWeight(newDish.getWeight());
        }
        session.update(dish);
    }

    @Transactional
    public Dish findById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Dish d where d.ID = :id");
        query.setParameter("id", id);

        return (Dish) query.uniqueResult();

    }

    @Transactional
    public int getMaxId() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session
                .createCriteria(Dish.class)
                .setProjection(Projections.max("id"));
        return (Integer)criteria.uniqueResult();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
