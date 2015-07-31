/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author Tomasz
 */
@Path("preflight")
public class Preflight 
{
    @OPTIONS
    public Response preflight()
    {    
        return Response.status(200).header("Access-Control-Allow-Credentials", "true") //allow to use cookies
          .header("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE")
          .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept").build();           
    }
}
