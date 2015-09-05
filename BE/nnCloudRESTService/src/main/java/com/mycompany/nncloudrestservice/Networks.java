/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice;

import com.mycompany.nncloudrestservice.controllers.GetListOfNetworksController;
import com.mycompany.nncloudrestservice.controllers.LoadController;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    @Path("{id : \\\\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_XML)
    public Response loadNetwork(@PathParam("id") String id)
    {
        LoadController lc = new LoadController();
        String r = lc.loadNetworkAsXML(Integer.parseInt(id));
        return Response.status(200).entity(r).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListOfNetworks()
    {
        GetListOfNetworksController glofnc = new GetListOfNetworksController();
        return Response.status(200).entity(glofnc.getListOfNetworks().toString()).build();
    }
}
