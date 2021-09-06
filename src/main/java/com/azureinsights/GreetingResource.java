package com.azureinsights;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.atomic.AtomicLong;

@Path("/greeting")
public class GreetingResource {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response greeting(@PathParam("name") String name) {
        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
        return Response.ok(greeting.toString()  ).build();
    }
}