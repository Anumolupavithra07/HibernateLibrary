package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Date;

public class ClientDemo
{
    public static void main(String[] args)
    {
        // Step 1: Configure Hibernate
        Configuration cfg = new Configuration();
        cfg.configure();
        cfg.addAnnotatedClass(Library.class);

        // Step 2: Create SessionFactory
        SessionFactory sf = cfg.buildSessionFactory();

        // Step 3: Open Session
        Session session = sf.openSession();

        // Step 4: Insert Record
        Transaction tx = session.beginTransaction();

        Library lib = new Library();
        lib.setName("Central Library");
        lib.setDescription("Books Collection");
        lib.setDate(new Date());
        lib.setStatus("Available");

        session.save(lib);

        tx.commit();
        System.out.println("Record Inserted Successfully");

        // Step 5: Delete Record by ID
        Transaction tx2 = session.beginTransaction();

        Library l = session.get(Library.class,1);

        if(l != null)
        {
            session.delete(l);
            System.out.println("Record Deleted Successfully");
        }

        tx2.commit();

        // Step 6: Close Session
        session.close();
        sf.close();
    }
}