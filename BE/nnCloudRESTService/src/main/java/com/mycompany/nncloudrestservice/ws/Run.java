/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.ws;

import com.mycompany.nncloudrestservice.localcalculations.RunManager;
import java.util.Arrays;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
@Path("run")
public class Run {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response run(String x)
    {
        JSONObject request = new JSONObject(x);

        JSONArray jVector= request.getJSONArray("vector");
        double vector[] = new double[jVector.length()];

        for(int i=0; i< vector.length ;i++)
            vector[i] = jVector.getDouble(i);

        RunManager manager = new RunManager(request.getInt("id_network"));
        double [] result = manager.run(vector);
        return Response.status(200).entity(Arrays.toString(result)).build();
    }
}
