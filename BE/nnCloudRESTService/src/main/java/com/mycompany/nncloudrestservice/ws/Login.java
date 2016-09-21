package com.mycompany.nncloudrestservice.ws;

import com.mycompany.nncloudrestservice.exceptions.LoginException;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
        com.mycompany.nncloudrestservice.model.Login lc = new com.mycompany.nncloudrestservice.model.Login();
        JSONObject request = new JSONObject(x);
                
        NewCookie c1;
        String uuid;
        
        try
        {            
            lc.loginUser(request);
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
        lc.saveSession(request, uuid);
        c1 = new NewCookie("session_id", uuid, "/", null, null, COOKIE_MAX_AGE, false);

        return Response.status(200).cookie(c1).build();
    }
}