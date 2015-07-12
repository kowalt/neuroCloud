/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.daos;

import com.mycompany.nncloudrestservice.model.User;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
public class UserDAOImpl implements UserDAO 
{
    private User user;
    
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    static //TODO refactorization!
    {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }
    
    
    @Override
    public User getUser(String login, String password) throws LoginException
    {
        Session session = factory.openSession();
        Transaction tx = null;
        user = null;
        try
        {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM users u WHERE u.login = :login AND u.password = :password");     
            query.setParameter("login", login);
            query.setParameter("password", password);
            List results = query.list();

            if(results.size() == 0)
                throw new LoginException("Incorrect credentials");
            
            user = (User)results.get(0);
            
            if(!user.isActivated())
                throw new LoginException("Incorrect login");
               
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
    
    @Override
    public void registerNewUser(JSONObject regData) throws UserExistsException
    {
        user = new User();
        user.setActivated(false);
        user.setEmail(regData.getString("email"));
        user.setLogin(regData.getString("login"));
        user.setInfo_to_admin(regData.getString("info_to_admin"));
        //set password
        String givenPassword = regData.getString("password");
        String salt = givenPassword.substring(0,2);
        String givenEncryptedPassword = DigestUtils.sha256Hex(salt+givenPassword);
        user.setPassword(givenEncryptedPassword);
        user.setRegistered(Calendar.getInstance().getTime());
        
        Session session = factory.openSession();
        Transaction tx = null;
                
        try
        {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM users u WHERE u.login = :login");      
            query.setParameter("login", user.getLogin());
            List results = query.list();
            
            if(results.size() > 0)
                throw new UserExistsException("User with this login already exists");
            
            query = session.createQuery("FROM users u WHERE u.email = :email");
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
    public void saveSession(String uuid)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        user.setSession_id(uuid);
        try
        {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }
        catch(HibernateException he)
        {
            if (tx!=null) tx.rollback();
            he.printStackTrace();
        }
        finally
        {
            session.close();
        }
    }
}
