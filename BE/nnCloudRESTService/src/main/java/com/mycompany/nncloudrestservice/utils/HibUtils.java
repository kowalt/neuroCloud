/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.utils;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.UnknownProfileException;
import org.hibernate.proxy.HibernateProxy;

/**
 *
 * @author Tomasz
 */
public class HibUtils {
    
public static void enableEagerFetching(Session s)
{
    try
    {     
        s.enableFetchProfile("user-with-networks");
        s.enableFetchProfile("network-with-layers");
        s.enableFetchProfile("layer-with-neurons");
        s.enableFetchProfile("neuron-with-synapses_in");
        s.enableFetchProfile("neuron-with-synapses_out");
        s.enableFetchProfile("neuron-with-activation_functions");
    }
    catch(UnknownProfileException e)
    {
        e.printStackTrace();
    }
}        

public static void disableEagerFetching(Session s)
{
    s.disableFetchProfile("user-with-networks");
    s.disableFetchProfile("network-with-layers");
    s.disableFetchProfile("layer-with-neurons");
    s.disableFetchProfile("neuron-with-synapses_in");
    s.disableFetchProfile("neuron-with-synapses_out");
    s.disableFetchProfile("neuron-with-activation_functions");
}
    
@Deprecated    
public static <T> T initializeAndUnproxy(T entity) 
{
    if (entity == null) 
        throw new NullPointerException("Entity passed for initialization is null");
    
    Hibernate.initialize(entity);
    
    if (entity instanceof HibernateProxy)
        entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
    
    return entity;
}

}
