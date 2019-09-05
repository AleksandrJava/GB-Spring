import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;

        //Customer customer = new Customer("Oleg");
        //Good good = new Good("pen", 300);


        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            //addProduct(session, good);
            //deleteCustomer(session, "Oleg");
            //addCustomer(session, customer);
            //doPurchase(session, "Slava", "book");
            //deleteProduct(session, "pen");
            //goodsWhichBuyCustomer(session, "Slava");
            customersWhichBuyGood(session, "book");
            session.getTransaction().commit();

        } finally {
            factory.close();
            session.close();
        }
    }

    public static void addCustomer(Session session, Customer customer){
        session.save(customer);
    }

    public static void deleteCustomer(Session session, String customer){
        Query query = session.createQuery("DELETE Customer a WHERE a.name = :lg");
        query.setParameter("lg", customer);
        query.executeUpdate();
    }

    public static void addProduct(Session session, Good good){
        session.save(good);
    }

    public static void deleteProduct(Session session, String good){
        Query query = session.createQuery("DELETE Good a WHERE a.title = :ttl");
        query.setParameter("ttl", good);
        query.executeUpdate();
    }

    public static void doPurchase(Session session, String customer, String good){
        Query query = session.createQuery("FROM Customer a where a.name = :paramName");
        query.setParameter("paramName", customer);
        Customer customerFromDB = (Customer)query.getSingleResult();

        Good goodFromDB = null;
        if(customerFromDB != null){
            Query query1 = session.createQuery("FROM Good a where a.title = :paramTitle");
            query1.setParameter("paramTitle", good);
            goodFromDB = (Good)query1.getSingleResult();
            if(goodFromDB != null){
                Purchase purchase = new Purchase(customerFromDB, goodFromDB, goodFromDB.getCost());
                session.save(purchase);
            }
        }
    }

    public static void goodsWhichBuyCustomer(Session session, String customer){
        Query query = session.createQuery("FROM Customer where name = :paramName");
        query.setParameter("paramName", customer);
        Customer customer1 = (Customer)query.getSingleResult();

        Query query1 = session.createQuery("FROM Purchase a where customer.id = :paramName");
        query1.setParameter("paramName", customer1.getId());
        ArrayList<Purchase> purchases = (ArrayList<Purchase>)query1.getResultList();

        for (Purchase a: purchases) {
            System.out.println(a);
        }
    }

    public static void customersWhichBuyGood(Session session, String good){
        Query query = session.createQuery("FROM Good where title = :paramName");
        query.setParameter("paramName", good);
        Good good1 = (Good)query.getSingleResult();

        Query query1 = session.createQuery("FROM Purchase a where good.id = :paramName");
        query1.setParameter("paramName", good1.getId());
        ArrayList<Purchase> purchases = (ArrayList<Purchase>)query1.getResultList();

        for (Purchase a: purchases) {
            System.out.println(a);
        }
    }

}
