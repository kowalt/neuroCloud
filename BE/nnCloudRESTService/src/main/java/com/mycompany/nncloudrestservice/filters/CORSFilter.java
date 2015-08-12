/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.filters;

import com.mycompany.nncloudrestservice.utils.TrustedOrigins;
import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

/**
 *
 * @author Tomasz
 */

public class CORSFilter implements ContainerResponseFilter
{
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException
    {
        String origin = requestContext.getHeaderString("Origin");
        if(TrustedOrigins.check(origin))
        {    
            responseContext.getHeaders().add("Access-Control-Allow-Origin", origin);
        }
    }        
}
