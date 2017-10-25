package com.hibernate.main;

import com.hibernate.model.UserDetails;
import com.hibernate.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HibernateTest {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        Vehicle vehicle2 = new Vehicle();

        UserDetails user = new UserDetails();
        user.setName("Ashmeet Tiwary");

        vehicle.setVehicleName("TATA");
        vehicle2.setVehicleName("CAr");
        //Many Vehicle 
        user.getVehicle().add(vehicle);
        user.getVehicle().add(vehicle2);

        vehicle.setUser(user);
        vehicle2.setUser(user);
//        vehicle2.getUser().add(user);
//        UserDetails user2 = new UserDetails();
//        
//        user2.setUserName("Pawan Tiwary");
//        user2.setJoinedDate(new Date());
//        user2.setDescription("This is test data");
//        addr.setCity("Lekhnath");
//        addr.setState("Nepal");
//        addr.setStreet("Rajchautara");
//        addr.setPincode("00977");
//        user2.setAddress(addr);
//        
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(user);

            Query q = session.createQuery("from User where id = :userId ");
            q.setParameter("userId", "4");
            UserDetails delUser = (UserDetails) q.list().get(0);

            for (Vehicle sdr : delUser.getVehicle()) {
                session.delete(sdr);
            }
            session.delete(delUser);

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

//
    }
}
