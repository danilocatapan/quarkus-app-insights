package com.azureinsights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/logging")
public class LoggingResource {

    Logger logger = LoggerFactory.getLogger(LoggingResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        logger.warn("This is a WARN message.");
        logger.error("This is an ERROR message.");

        return "Welcome to Quarkus Logging! Check the console to see the log messages.";
    }
}
