package com.mycompany.nncloudrestservice;

import com.mycompany.nncloudrestservice.daos.LoginException;
import com.mycompany.nncloudrestservice.daos.UserDAO;
import com.mycompany.nncloudrestservice.daos.UserDAOImpl;
import com.mycompany.nncloudrestservice.model.User;
import com.mycompany.nncloudrestservice.utils.TrustedOrigins;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
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
     * @param origin
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(@HeaderParam("Origin") String origin, String x) 
    {
        JSONObject request = new JSONObject(x);
        
        String givenLogin = request.get("login").toString();
        
        // DigestUtils.sha256Hex(password + "salt");
        String givenPassword = request.get("password").toString();
        String salt = givenPassword.substring(0,2);
        String givenEncryptedPassword = DigestUtils.sha256Hex(salt+givenPassword);
        
        UserDAO udao = new UserDAOImpl();
        
        Cookie c1;
        String uuid;
        
        try
        {
            if(!TrustedOrigins.check(origin))
                throw new UntrustedOriginException();
            
            User u = udao.getUser(givenLogin, givenEncryptedPassword);
        }
        catch(UntrustedOriginException uoe)
        {
            JSONObject error = new JSONObject();
            error.put("error", uoe.getMessage());
            return Response.status(401).header("Access-Control-Allow-Origin", origin).entity(error.toString()).build();//TODO try without toString   
        }    
        catch(LoginException le)
        {
            JSONObject error = new JSONObject();
            error.put("error", le.getMessage());
            uuid = String.valueOf(0); //TODO save to database
            c1 = new Cookie("session_id", uuid);
            return Response.status(401).header("Access-Control-Allow-Origin", origin).cookie(new NewCookie(c1)).entity(error.toString()).build();
        }
        
        uuid = UUID.randomUUID().toString();
        udao.saveSession(uuid);
        c1 = new Cookie("session_id", uuid);
        return Response.status(200).header("Access-Control-Allow-Origin", origin).cookie(new NewCookie(c1)).build();
    }
}
