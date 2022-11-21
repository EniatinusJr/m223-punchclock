package ch.zli.m223.controller;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.service.AuthService;

@Path("/login")
public class AuthController {

    @Inject
    AuthService authService;
    
    @POST
    @Path("/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index user.", description = "Returns a JWT if login successful.")
    public String authlogin(@PathParam("username") String username, @PathParam("password") String password) {
        if(authService.userExists(username, password) == true){
            return authService.generateToken();
        }else{
            return null;
        }
    }

}
