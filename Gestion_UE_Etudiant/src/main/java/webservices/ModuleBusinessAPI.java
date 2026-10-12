package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class ModuleBusinessAPI {
    ModuleBusiness helper = new ModuleBusiness();
    UniteEnseignementBusiness ueHelper = new UniteEnseignementBusiness();


    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListModules() {
        return Response
                .status(200)
                .entity(helper.getAllModules())
                .build();
    }
    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createModule(Module module) {
        if (module.getUniteEnseignement() == null) {
            return Response.status(400).entity("UE obligatoire").build();
        }
        if (helper.addModule(module)) {
            return Response.status(201).entity("added successfully").build();
        }
        return Response.status(400).entity("didn't get added").build();
    }}

