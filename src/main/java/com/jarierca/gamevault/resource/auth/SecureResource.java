package com.jarierca.gamevault.resource.auth;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/secure")
public class SecureResource {

    @GET
    @RolesAllowed("user")
    public Response getSecureInfo() {
        return Response.ok("This is a secure endpoint").build();
    }
}
