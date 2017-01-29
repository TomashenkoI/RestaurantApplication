package ua.goit.java.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.goit.java.dao.model.Menu;
import ua.goit.java.dao.DishToOrderDAO;

import java.util.List;
import java.util.Scanner;

public class MenuDAO{

    private DishToOrderDAO dishToOrderDAO = new DishToOrderDAO();

    private SessionFactory sessionFactory;

    public void save(Menu menu) {
        Session session = sessionFactory.getCurrentSession();
        session.save(menu);
    }

    public void remove(Menu menu) {
        sessionFactory.getCurrentSession().delete(menu);
    }

    public Menu findByName() {

        Session session = sessionFactory.getCurrentSession();
        System.out.println("Введите название :");
        String name = new Scanner(System.in).nextLine();
        Query query = session.createQuery("select m from Menu m where m.name like :name");
        query.setParameter("name", name);

        return (Menu) query.uniqueResult();

    }

    public List<Menu> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from Menu m");
        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
