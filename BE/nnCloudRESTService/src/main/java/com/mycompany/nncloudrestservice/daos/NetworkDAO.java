/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.daos;

import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.User;
import com.mycompany.nncloudrestservice.utils.CurrentUserContainer;
import com.mycompany.nncloudrestservice.utils.HibUtils;
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
public class NetworkDAO implements DAO<Network>
{
    private final SessionFactory factory;
    private boolean lazyLoadMode = true;
    
    public NetworkDAO()
    {
        this.factory = SessionContainer.factory;
    }
    
    @Override
    public void addItem(Network item)
    {        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery("FROM com.mycompany.nncloudrestservice.model.User u WHERE u.id = :id_user");
            
            query.setParameter("id_user", CurrentUserContainer.getInstance().getId());
            List results = query.list();
            User u = (User)results.get(0);
            
            List<Network> finalList = u.getNetworks();
            item.setUser(u);
            finalList.add(item);
            u.setNetworks(finalList);

            session.update(u);
            
            tx.commit();
        }
        catch(HibernateException he)
        {
            if (tx != null) tx.rollback();
            he.printStackTrace();
        }
        catch(Exception e)
        {
            tx.rollback();
            e.printStackTrace();
        }
        finally
        {
            try
            {
                session.close();
            }
            catch(HibernateException he)
            {
                he.printStackTrace();
            }    
            System.out.println("Session closed");
        }
    }

    @Override
    public void updateItem(Network item) {
        Session session = factory.openSession();
        Transaction tx = null;
        
        try
        {
            tx = session.beginTransaction();
            session.update(item);
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
    public void removeItem(Network item) {
        Session session = factory.openSession();
        Transaction tx = null;
        
        try
        {
            tx = session.beginTransaction();
            session.delete(item);
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
    public Network getItem(String... keys) throws Exception {
        Session session = factory.openSession();
                
        if(!lazyLoadMode)  
            HibUtils.enableEagerFetching(session);
 
        Transaction tx = null;
        
        Integer id_network = Integer.parseInt(keys[0]);
        
        Network n = null;
        
        try
        {
            tx = session.beginTransaction();
            
            n = (Network) session.get(Network.class, id_network); 
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
        
        return n;
    }
    
    public List<Network> getNetworksForCurrentUser()
    {
        SessionFactory factory = SessionContainer.factory;
        Session session = factory.openSession();
        Transaction tx = null;
        List<Network> networks = null;
        try
        {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT n FROM com.mycompany.nncloudrestservice.model.Network n JOIN n.user user WHERE user.id = :userid");
            query.setParameter("userid", CurrentUserContainer.getInstance().getId());
            networks = query.list();
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
        return networks;
    }

    public void setLazyLoadMode(boolean lazyLoadMode) {
        this.lazyLoadMode = lazyLoadMode;
    }
}
