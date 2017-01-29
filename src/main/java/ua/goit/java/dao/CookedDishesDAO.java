package ua.goit.java.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.goit.java.dao.model.CookedDish;

import java.util.List;
import java.util.Scanner;

public class CookedDishesDAO {

    private ListOfIngredientsDAO listOfIngredientsDAO;
    private StorageDAO storageDAO;
    private IngredientsDAO ingredientsDAO;

    private SessionFactory sessionFactory;

    public void save(CookedDish cookedDish) {
        Session session = sessionFactory.getCurrentSession();
        session.save(cookedDish);
    }

    public void remove(CookedDish cookedDish) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(cookedDish);
    }

    public CookedDish findByName() {

        Session session = sessionFactory.getCurrentSession();
        System.out.println("Введите название блюда: ");
        String enteredName = new Scanner(System.in).nextLine();
        Query query = session.createQuery("select c from CookedDish c where c.dish = :name");
        query.setParameter("name", enteredName);

        return (CookedDish) query.uniqueResult();
    }

    public List<CookedDish> findAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CookedDish c");

        return query.list();

    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void setListOfIngredientsDAO(ListOfIngredientsDAO listOfIngredientsDAO) {
        this.listOfIngredientsDAO = listOfIngredientsDAO;
    }

    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    public void setIngredientsDAO(IngredientsDAO ingredientsDAO) {
        this.ingredientsDAO = ingredientsDAO;
    }
}
