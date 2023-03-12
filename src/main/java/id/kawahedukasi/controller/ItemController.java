package id.kawahedukasi.controller;

import com.opencsv.exceptions.CsvValidationException;
import id.kawahedukasi.DTO.FileFormDTO;
import id.kawahedukasi.service.ExportService;
import id.kawahedukasi.service.ImportService;
import id.kawahedukasi.service.ItemService;
import net.sf.jasperreports.engine.JRException;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {
    @Inject
    ItemService itemService;

    @Inject
    ExportService exportService;

    @Inject
    ImportService importService;

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

    @GET
    @Path("/export/pdf")
    @Produces("application/pdf")
    public Response exportPdf() throws JRException {
        return exportService.exportPdfItem();
    }

    @POST
    public Response post(Map<String, Object> request){
        return itemService.post(request);
    }

    // Import csv
    @POST
    @Path("/import/csv")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response importCSV(@MultipartForm FileFormDTO request){
        try {
            return importService.importCSV(request);
        } catch (IOException | CsvValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
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
