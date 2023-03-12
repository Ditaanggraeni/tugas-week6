package id.kawahedukasi.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import net.sf.jasperreports.engine.JRException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class MailService {
    @Inject
    Mailer mailer;

    @Inject
    ExportService exportService;

    public void sendEmail(String email) {
        mailer.send(
                Mail.withHtml(email,
                        "Tugas CRUD API",
                        "<h1>Hello,</h1> this is Tugas CRUD API Quarkus"));
    }

    public void sendPdfToEmail(String email) throws IOException, JRException {
        mailer.send(
                Mail.withHtml(email,
                                "Laporan Penjualan Item",
                                "<h1>Hello,</h1> this is your pdf file")
                        .addAttachment("Data-penjualan-item.pdf",
                                exportService.exportPdfItem().readEntity(byte[].class),
                                "application/pdf"));
    }
}
