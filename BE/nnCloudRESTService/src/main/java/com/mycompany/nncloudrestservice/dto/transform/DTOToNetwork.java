/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.dto.transform;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.dto.NetworkDTO;
import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;

/**
 *
 * @author Tomasz
 */
public class DTOToNetwork {
    public Network transform(NetworkDTO ndto)
    {
       Network n = new Network();
       
       n.setId(ndto.getId());
       
       //obtain creation from database if network already exists
       NetworkDAO ndao = new NetworkDAO();
       //n.setCreation(ndao.getItem(keys));
       
       return n;
    }
}
