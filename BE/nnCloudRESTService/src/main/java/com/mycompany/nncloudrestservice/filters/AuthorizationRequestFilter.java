/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.filters;

import com.mycompany.nncloudrestservice.model.Login;
import com.mycompany.nncloudrestservice.utils.CurrentUserContainer;
import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;

/**
 *
 * @author Tomasz
 */
@PreMatching
@Priority(2)
public class AuthorizationRequestFilter implements ContainerRequestFilter
{
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {
        // don't check the credentials if not Login or Register request or OPTIONS method
        String path = requestContext.getUriInfo().getPath();
        if(requestContext.getMethod().matches("OPTIONS") || path.matches("(login|register)"))
            return;

        Cookie c = requestContext.getCookies().get("session_id");
        
        Login lc = new Login();
        
        // check if cookie contains a valid token 
        if(c != null && c.getValue() != null && lc.checkIfTokenIsCorrect(c.getValue()))
        {   
            CurrentUserContainer.loadInstance(c.getValue());
            return;
        }
        
        //Dismiss
        requestContext.abortWith(Response.status(401).build());
    }
}
