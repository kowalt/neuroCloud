/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.filters;

import com.mycompany.nncloudrestservice.Main;
import java.io.IOException;
import java.net.URI;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;

/**
 *
 * @author Tomasz
 */
@Priority(1)
@PreMatching
public class CORSPreflightFilter implements ContainerRequestFilter 
{
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {   
        try
        {
            if(requestContext.getMethod().matches("OPTIONS"))
                requestContext.setRequestUri(URI.create(Main.base_uri + "preflight"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
