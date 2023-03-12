package id.kawahedukasi.service;

import id.kawahedukasi.model.Item;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.HashMap;

@ApplicationScoped
public class ExportService {
    public Response exportPdfItem() throws JRException {
        //load template jasper
        File file = new File("src/main/resources/ItemReport.jrxml");

        //build object jasper report dari load template
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        //create datasource jasper for all data item
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(Item.listAll());

        //create object jasperPrint
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>() , jrBeanCollectionDataSource);

        //export jasperPrint to byte array
        byte[] jasperResult = JasperExportManager.exportReportToPdf(jasperPrint);

        //return response dengan content type pdf
        return Response.ok().type("application/pdf").entity(jasperResult).build();

    }
}
