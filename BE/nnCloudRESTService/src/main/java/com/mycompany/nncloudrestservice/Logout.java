/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice;

import com.mycompany.nncloudrestservice.daos.UserDAO;
import com.mycompany.nncloudrestservice.daos.UserDAOImpl;
import com.mycompany.nncloudrestservice.utils.DomainFromURLUtil;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
@Path("logout")
public class Logout 
{    
    @GET
    public Response logoutUser(@CookieParam("session_id") Cookie cookie)
    {
        UserDAO udao = new UserDAOImpl();
      
        try
        {
            udao.destroySession(cookie.getValue());
        }
        catch(LogoutException le)
        {
            JSONObject error = new JSONObject();
            error.put("error", le.getMessage());
            return Response.status(401).entity(error).build();
        }
        
        NewCookie killer = new NewCookie("session_id","0", "/",null,null,0,false);

        return Response.status(204).cookie(killer).build();
    }      
}
