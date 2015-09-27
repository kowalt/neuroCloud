/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.utils;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

/**
 *
 * @author Tomasz
 */
public class HibUtils {
    
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
