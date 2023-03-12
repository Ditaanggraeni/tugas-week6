package id.kawahedukasi.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import id.kawahedukasi.DTO.FileFormDTO;
import id.kawahedukasi.model.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ImportService {
    @Transactional
    public Response importCSV(FileFormDTO request) throws IOException, CsvValidationException {
        File file = File.createTempFile("temp", "");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(request.file);

        CSVReader reader = new CSVReader(new FileReader(file));
        String[] nextLine;
        reader.skip(1);

        List<Item> toPersist = new ArrayList<>();
        //read one line at a time
        while ((nextLine = reader.readNext()) != null) {
            Item item = new Item();
            item.name = nextLine[0];
            item.count = Double.valueOf(nextLine[1].replace("$", ""));
            item.price = Double.valueOf(nextLine[2].replace("$", ""));
            item.type = nextLine[3];
            item.description = nextLine[4];
            toPersist.add(item);
            item.persist(toPersist);

        }
        return Response.status(Response.Status.CREATED)
                .entity(Map.of("message", "SUCCESS"))
                .build();
    }
}
