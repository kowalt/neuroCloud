/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.daos;


import com.mycompany.nncloudrestservice.controllers.LoginException;
import com.mycompany.nncloudrestservice.model.User;
import com.mycompany.nncloudrestservice.utils.SessionContainer;
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
public class UserDAO implements DAO<User>
{    
    private SessionFactory factory;
    
    public UserDAO()
    {
        this.factory = SessionContainer.factory;
    }
    
    @Override
    public void addItem(User user) throws UserExistsException
    {
        Session session = factory.openSession();
        Transaction tx = null;
                
        try
        {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM User u WHERE u.login = :login");      
            query.setParameter("login", user.getLogin());
            List results = query.list();
            
            if(results.size() > 0)
                throw new UserExistsException("User with this login already exists");
            
            query = session.createQuery("FROM User u WHERE u.email = :email");
            query.setParameter("email", user.getEmail());
            
            if(results.size() > 0)
                throw new UserExistsException("User with this email already exists");
            
            session.save(user);
            tx.commit();
        }
        catch(HibernateException he)
        {
            if (tx != null) tx.rollback();
            he.printStackTrace();
        }
        finally
        {
            session.close();
        }
    }
    
    @Override
    public void updateItem(User user)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        
        try
        {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }
        catch(HibernateException he)
        {
            if( tx != null) tx.rollback();
            he.printStackTrace();
        }
        finally
        {
            session.close();
        }
    }
    
    @Override
    public void removeItem(User user)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        
        try
        {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        }
        catch(HibernateException he)
        {
            if (tx != null) tx.rollback();
            he.printStackTrace();
        }
        finally
        {
            session.close();
        }
    }
    
    @Override
    public User getItem(String... keys) throws LoginException
    {
        String login = keys[0];
        String password = keys[1];
        
        Session session = factory.openSession();
        Transaction tx = null;
        User user = null;

        try
        {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM com.mycompany.nncloudrestservice.model.User u WHERE u.login = :login AND u.password = :password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            List results = query.list();
            
            if(!results.isEmpty())            
                user = (User)results.get(0);
            
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
        return user;
    }
    
    public User getItem(String token)
    {
        Session session = factory.openSession();
        
        Transaction tx = null;
        User user = null;
        
        try
        {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM com.mycompany.nncloudrestservice.model.User u WHERE u.session_id = :token");
            query.setParameter("token", token);
            List results = query.list();
            
            if(!results.isEmpty())
                user = (User)results.get(0);
            
            tx.commit();
        }    
        catch(HibernateException e)
        {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return user;
    }
}
