package com.mycompany.nncloudrestservice;

import com.mycompany.nncloudrestservice.daos.LoginException;
import com.mycompany.nncloudrestservice.daos.UserDAO;
import com.mycompany.nncloudrestservice.daos.UserDAOImpl;
import com.mycompany.nncloudrestservice.model.User;
import com.mycompany.nncloudrestservice.utils.DomainFromURLUtil;
import com.mycompany.nncloudrestservice.utils.SafeHashUtil;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("login")
public class Login 
{
    private static final int COOKIE_MAX_AGE=1209600; //Two weeks ;)
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
    public Response loginUser(String x) 
    {
        JSONObject request = new JSONObject(x);
        // TODO: Too boilerplate. Take advantage of ResponseBuilder!
        String givenLogin = request.get("login").toString();
        
        // DigestUtils.sha256Hex(password + "salt");
        
        String givenEncryptedPassword = SafeHashUtil.getHash(request.get("password").toString());
        
        UserDAO udao = new UserDAOImpl();
        
        NewCookie c1;
        String uuid;
        
        try
        {            
            User u = udao.getUser(givenLogin, givenEncryptedPassword);
        } 
        catch(LoginException le)
        {
            JSONObject error = new JSONObject();
            error.put("error", le.getMessage());
            uuid = String.valueOf(0);

            c1 = new NewCookie("session_id", uuid, "/", null, null, COOKIE_MAX_AGE, false); //TODO: Set to true after implementing HTTPS!
            return Response.status(401).cookie(c1).entity(error.toString()).build();
        }
        
        uuid = UUID.randomUUID().toString();
        udao.saveSession(uuid);
        c1 = new NewCookie("session_id", uuid, "/", null, null, COOKIE_MAX_AGE, false);

        return Response.status(200).cookie(c1).build();
    }
}