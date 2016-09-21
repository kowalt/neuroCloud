package com.mycompany.nncloudrestservice.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.pojo.Network;

@Path("downloadOutputVector")
public class DownloadOutputVector {
    @GET
    @Path("/{network_id: \\d+}")
    @Produces(MediaType.APPLICATION_XML)
    public Response downloadOutputVector(@PathParam("id") Integer id)
    {
    	NetworkDAO ndao = new NetworkDAO();
    	
    	Network network = ndao.getItem(id);
    	
    	network.getLayers().get(2).getNeurons();
    	
    	// for()
    	
    	return Response.status(200).
    }
}
