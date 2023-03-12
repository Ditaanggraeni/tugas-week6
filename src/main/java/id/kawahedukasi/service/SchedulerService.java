package id.kawahedukasi.service;

import io.quarkus.scheduler.Scheduled;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class SchedulerService {
    @Inject
    MailService mailService;

    Logger logger = LoggerFactory.getLogger(SchedulerService.class);
    @Scheduled(cron = "0/10 30 12 12 * ? *")
    public void scheduleSendEmailPdf() throws JRException, IOException {
        mailService.sendPdfToEmail("ditaa1099@gmail.com");
        logger.info("SEND EMAIL SUCCESS");
    }
}
