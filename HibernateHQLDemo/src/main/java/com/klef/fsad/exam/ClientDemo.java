package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        // Get SessionFactory
        SessionFactory sf = HibernateUtil.getSessionFactory();

        // -----------------------------
        // I. Insert Record
        // -----------------------------
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Restaurant r = new Restaurant();
        r.setName("Spice Garden");
        r.setDate(new Date());
        r.setStatus("Open");
        r.setLocation("Hyderabad");
        r.setCuisine("Indian");

        session.save(r);

        tx.commit();
        session.close();

        System.out.println("Restaurant Record Inserted Successfully");

        // -----------------------------
        // II. Update using HQL
        // -----------------------------
        Session session2 = sf.openSession();
        Transaction tx2 = session2.beginTransaction();

        String hql = "update Restaurant set name=:name, status=:status where id=:id";

        Query<?> query = session2.createQuery(hql);
        query.setParameter("name", "Food Palace");
        query.setParameter("status", "Closed");
        query.setParameter("id", 1);

        int rows = query.executeUpdate();

        tx2.commit();
        session2.close();

        System.out.println("Rows Updated: " + rows);

        sf.close();
    }
}