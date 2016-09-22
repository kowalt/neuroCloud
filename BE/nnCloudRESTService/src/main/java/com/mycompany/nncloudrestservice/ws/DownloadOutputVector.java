package com.mycompany.nncloudrestservice.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.nncloudrestservice.logic.GetOutput;
import org.json.JSONArray;

@Path("downloadOutputVector")
public class DownloadOutputVector {
    @GET
    @Path("/{network_id: \\d+}")
    @Produces(MediaType.APPLICATION_XML)
    public Response downloadOutputVector(@PathParam("id") Integer id)
    {  
        GetOutput go = new GetOutput(id);
        
        String result = go.validate(id);

        if(!result.equals("OK"))
            return Response.status(Response.Status.NOT_FOUND).build();
        
        JSONArray r = go.getOutput(id);
    	return Response.status(200).entity(r).build();
    }
}
