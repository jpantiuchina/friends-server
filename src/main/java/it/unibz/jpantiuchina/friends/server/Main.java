package it.unibz.jpantiuchina.friends.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;


public final class Main
{
    public static final String BASE_URI = "http://0.0.0.0:34712";


    public static HttpServer startServer()
    {
        // create a resource config that scans for JAX-RS resources and providers
        ResourceConfig rc = new ResourceConfig().packages(Main.class.getPackage().getName());

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }


    public static void main(String[] args)
    {
        startServer();
        System.out.println("Friends Server started");
        System.out.println("WADL available at " + BASE_URI + "/application.wadl");
        System.out.println("Try " + BASE_URI + "/get-my-friends?group=my-group&name=Katy&lat=56&lng=25");
    }
}

