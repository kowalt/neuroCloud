package com.mycompany.nncloudrestservice.ws;

import com.mycompany.nncloudrestservice.logic.TrainingManager;
import com.mycompany.nncloudrestservice.logic.TrainingProgressManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("training")
public class Training {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response training(String x)
    {
        TrainingManager trainingManager = new TrainingManager();
        trainingManager.train(x);
    	return Response.status(202).build();
    }
    
    @GET
    @Path("/{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtainProgress(@PathParam("id") String id)
    {
        TrainingProgressManager progressManager = new TrainingProgressManager();
        return Response.status(Response.Status.OK).entity(progressManager.getProgress(id)).build();
    }
}
