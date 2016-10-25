package com.mycompany.nncloudrestservice.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.User;
import com.mycompany.nncloudrestservice.utils.CurrentUserContainer;
import com.mycompany.nncloudrestservice.utils.SessionContainer;

public class NeuronDAO implements DAO<Neuron> {
    private final SessionFactory factory;
    
    public NeuronDAO()
    {
        this.factory = SessionContainer.factory;
    }

	@Override
	public void addItem(Neuron item) {
        Session session = factory.openSession();
        Transaction tx = null;
        
        try
        {
            tx = session.beginTransaction();
            session.saveOrUpdate(item);
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
        }
	}

	@Override
	public void updateItem(Neuron item) {
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
        }
	}

	@Override
	public void removeItem(Neuron item) {
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
        }
	}

	@Override
	public Neuron getItem(String... keys) {
        Session session = factory.openSession();
        Transaction tx = null;
        Neuron n = null;
        try
        {
	        Query query = session.createQuery("FROM com.mycompany.nncloudrestservice.pojo.Neuron n WHERE n.id = :id");
	        query.setParameter("id", keys[0]);
	
	        List results = query.list();
	        n = (Neuron)results.get(0);

	        session.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        finally
        {
        	session.close();
        }
        return n;
	}
}
