package id.kawahedukasi.controller;

import id.kawahedukasi.model.Item;
import id.kawahedukasi.service.ItemService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {
    @Inject
    ItemService itemService;

    // List all item
    @GET
    public Response get() {
        return itemService.get();
    }

    // Detail Item by id
    @Path("/{id}")
    @GET
    public Response get(@PathParam("id") Long id) {
        return itemService.get(id);
    }

    @POST
    public Response post(Map<String, Object> request){
        return itemService.post(request);
    }

    @PUT
    @Path("/{id}")
    public Response put(@PathParam("id") Long id, Map<String, Object> request){
        return itemService.put(id, request);
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        return itemService.delete(id);
    }
}
