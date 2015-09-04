/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice;

import com.mycompany.nncloudrestservice.model.Network;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Tomasz
 */
@Path("networks")
public class Networks
{
    @GET
    @Path("/{id : \\\\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loadNetwork()
    {
        
        return null;
    }
}
