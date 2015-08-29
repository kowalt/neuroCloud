/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.daos;

import com.mycompany.nncloudrestservice.model.Network;
import com.mycompany.nncloudrestservice.utils.SessionContainer;
import org.hibernate.SessionFactory;

/**
 *
 * @author Tomasz
 */
public class NetworkDAO implements DAO<Network>
{
    private final SessionFactory factory;
    
    public NetworkDAO()
    {
        this.factory = SessionContainer.factory;
    }
    
    @Override
    public void addItem(Network item) throws Exception {
        
    }

    @Override
    public void updateItem(Network item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeItem(Network item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Network getItem(String... keys) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
