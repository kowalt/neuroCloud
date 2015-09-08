package com.mycompany.nncloudrestservice;

import com.mycompany.nncloudrestservice.filters.AuthorizationRequestFilter;
import com.mycompany.nncloudrestservice.filters.CORSFilter;
import com.mycompany.nncloudrestservice.filters.CORSPreflightFilter;
import com.mycompany.nncloudrestservice.filters.CurrentUserFilter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static String base_uri = "http://localhost:8080/nncloudAPI/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.mycompany.nncloudrestservice package
        final ResourceConfig rc = new ResourceConfig().packages(true, "com.mycompany.nncloudrestservice");

        Map<String, Object> customProperties = new HashMap<>();   
        customProperties.put("jersey.config.server.wadl.disableWadl", "true");
        rc.addProperties(customProperties);
        rc.register(new AuthorizationRequestFilter());
        rc.register(new CORSFilter());
        rc.register(new CORSPreflightFilter());
        rc.register(new CurrentUserFilter());
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(base_uri), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException 
    {        
        if(args.length != 0)
            base_uri = args[0];
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started at %s\nHit enter to stop it",base_uri));
        System.in.read();
        server.stop();
    }
}