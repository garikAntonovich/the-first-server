package by.iharantanovich.thefirstserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileUploadService {

    ParseXmlService parseXmlService;

    @Autowired
    public FileUploadService(ParseXmlService xmlService) {
        this.parseXmlService = xmlService;
    }

    public void readZip(MultipartFile file) {

        try {
            ZipInputStream zis = new ZipInputStream(file.getInputStream());
            ZipEntry zipEntry;
            Map<String, String> zipFiles = new HashMap<>();

            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".xml")) {
                    byte[] buffer = zis.readNBytes((int) zipEntry.getSize());
                    zipFiles.put(zipEntry.getName(), new String(buffer));
                } else {
                    continue;
                }
                zis.closeEntry();
            }
            zis.close();
            parseXmlService.parseXmlFiles(zipFiles);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}