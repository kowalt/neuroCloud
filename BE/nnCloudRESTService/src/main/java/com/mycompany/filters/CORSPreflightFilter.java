/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.filters;

import com.mycompany.nncloudrestservice.Main;
import java.io.IOException;
import java.net.URI;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import org.glassfish.jersey.server.Uri;

/**
 *
 * @author Tomasz
 */
public class CORSPreflightFilter implements ContainerRequestFilter 
{
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {
        if(requestContext.getMethod().matches("OPTIONS"))
             requestContext.setRequestUri(URI.create(Main.base_uri + "preflight"));
    }
}
