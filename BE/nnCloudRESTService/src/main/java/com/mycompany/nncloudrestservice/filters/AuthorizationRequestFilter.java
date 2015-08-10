/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.filters;

import com.mycompany.nncloudrestservice.daos.UserDAO;
import com.mycompany.nncloudrestservice.daos.UserDAOImpl;
import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Cookie;

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
        if(requestContext.getMethod().matches("OPTIONS") || path.matches("[login|register]"))
            return;

        Cookie c = requestContext.getCookies().get("session_id");
        
        UserDAO udao = new UserDAOImpl();
        
        // check if cookie contains a valid token 
        if(udao.checkIfTokenIsCorrect(c.getValue()))
            return;
            
        //Dismiss
    }
}
