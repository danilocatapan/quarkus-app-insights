package com.azureinsights;

import com.microsoft.applicationinsights.TelemetryClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/logging")
public class LoggingResource {
    static final TelemetryClient telemetryClient = new TelemetryClient();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void index() {
        Map<String, String> properties = new HashMap<>();
        properties.put("properties", "PROPERTIES OF PROPERTIES");

        Map<String, Double> metrics = new HashMap<>();
        metrics.put("metrics", 10.0);

        telemetryClient.trackEvent("telemetryClient trackEvent test", properties, metrics);
    }
}
