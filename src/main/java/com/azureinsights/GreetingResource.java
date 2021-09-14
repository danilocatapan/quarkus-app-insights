package com.azureinsights;

import com.microsoft.applicationinsights.TelemetryClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Path("/greeting")
public class GreetingResource {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    static final TelemetryClient telemetryClient = new TelemetryClient();

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response greeting(@PathParam("name") String name) {

        Map<String, String> properties = new HashMap<>();
        properties.put("NAME", name);

        Map<String, Double> metrics = new HashMap<>();
        metrics.put("metrics", 10.0);

        telemetryClient.trackEvent("INIT GREETING REQUEST");
        telemetryClient.trackEvent("GREETING PROPERTIES AND METRICS", properties, metrics);

        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));

        telemetryClient.trackEvent("MOUNTED GREETING REQUEST");

        String hello = greeting.toString();
        telemetryClient.trackEvent("FORMATTED HELLO: " + hello);

        return Response.ok(hello).build();
    }
}