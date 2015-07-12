package com.mycompany.nncloudrestservice;

import com.mycompany.nncloudrestservice.daos.UserDAOImpl;
import com.mycompany.nncloudrestservice.model.User;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import org.apache.commons.codec.digest.*;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("login")
public class Login 
{
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(String x) 
    {
        JSONObject request = new JSONObject(x);
        String givenLogin = request.get("login").toString();
        
        // DigestUtils.sha256Hex(password + "salt");
        String givenPassword = request.get("password").toString();
        String salt = givenPassword.substring(0,2);
        String givenEncryptedPassword = DigestUtils.sha256Hex(salt+givenPassword);
        
        User u = new UserDAOImpl().getUser(givenLogin, givenEncryptedPassword);
        
        if(!givenLogin.equals(u.getLogin()) || givenEncryptedPassword.equals(u.getPassword()))
        {
            JSONObject error = new JSONObject();
            error.put("error","Incorrect login or password");
            
            return Response.status(401).entity(error).build();
        }
            
        return Response.status(200).entity(salt).build();
    }
}
