/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.filters;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author Tomasz
 */
public class AuthorizationRequestFilter implements ContainerRequestFilter
{
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {
        final SecurityContext securityContext = requestContext.getSecurityContext();    
    }
}
