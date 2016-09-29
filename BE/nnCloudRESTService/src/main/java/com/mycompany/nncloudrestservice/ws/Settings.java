/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.nncloudrestservice.logic.ListOfServersManager;
import com.mycompany.nncloudrestservice.utils.ServerListContainer;
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
@Path("settings")
public class Settings {
   
    @GET
    @Path("/serverList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getServerList()
    {
        ListOfServersManager slManager = new ListOfServersManager();
        return Response.status(Response.Status.OK).entity(slManager.getListOfServers()).build();
    }
    
    @GET
    @Path("/newServer/{id: \\\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setActiveServer(@PathParam("id") String id)
    {
        ListOfServersManager losm = new ListOfServersManager();
        losm.setActiveServer(Integer.parseInt(id));
        return Response.status(204).build();
    }
}
