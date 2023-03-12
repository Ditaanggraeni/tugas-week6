package id.kawahedukasi.controller;

import id.kawahedukasi.service.MailService;
import net.sf.jasperreports.engine.JRException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Path("/mail")
@Produces("application/json")
@Consumes("application/json")
public class MailController {
    @Inject
    MailService mailService;
    @POST
    public Response sendEmail(Map<String, Object> request) throws IOException {
        mailService.sendEmail(request.get("email").toString());
        return Response.ok(Map.of("message", "SUCCESS")).build();
    }
    @POST
    @Path("/pdf")
    public Response sendPdfToEmail(Map<String, Object> request) throws IOException, JRException {
        mailService.sendPdfToEmail(request.get("email").toString());
        return Response.ok(new HashMap<>()).build();
    }
}
