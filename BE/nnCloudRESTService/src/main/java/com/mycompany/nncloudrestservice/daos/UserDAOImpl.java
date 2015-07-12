/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.daos;

import com.mycompany.nncloudrestservice.model.User;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Tomasz
 */
public class UserDAOImpl implements UserDAO 
{
    private static SessionFactory factory;
    @Override
    public User getUser(String login, String pasword)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        User u = null;
        try
        {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM users u WHERE u.login = :login");
            
            query.setParameter("login", login);
            
            List results = query.list();
            
            u = (User)results.get(0);
            
            tx.commit();
        }
        catch(HibernateException e)
        {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }
        finally
        {
            session.close();
        }
        return u;
    }
}
