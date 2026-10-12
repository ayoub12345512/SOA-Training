package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/ue")
public class UniteEnsRestAPI {
    //helper : instance that will help rhis
    // class to manipulate data : CRUD
    UniteEnseignementBusiness helper=
            new UniteEnseignementBusiness();
    //get list UEs
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListUEs(){

        return Response
                .status(200)
                .entity(helper.getListeUE())
                .build();
    }
    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUe(UniteEnseignement unite){
        if (helper.addUniteEnseignement(unite)){
            return Response.status(201).entity("added successfully").build();
        }
        else return Response.status(400).entity("didn't get added").build();

    }
    @Path("/search/{code}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByCode(@PathParam("code") int code) {
        return Response.ok(helper.getUEByCode(code)).build();
    }
    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response SearchBySem(@QueryParam("semestre") int semestre){
        return Response.ok(helper.getUEBySemestre(semestre)).build();
    }
    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUEbyCode(@QueryParam("code") int code){
        return Response.ok(helper.deleteUniteEnseignement(code)).build();
    }
}