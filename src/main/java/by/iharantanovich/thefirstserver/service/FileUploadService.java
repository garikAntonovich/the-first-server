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

    XmlService xmlService;

    @Autowired
    public FileUploadService(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    public void readZip(MultipartFile file) {

        Map<String, String> xmlFiles = new HashMap<>();

        try {
            ZipInputStream zis = new ZipInputStream(file.getInputStream());
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                byte[] bytes = zis.readNBytes((int) zipEntry.getSize());
                xmlFiles.put(zipEntry.getName(), new String(bytes));
                zis.closeEntry();
            }
            zis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        xmlService.storageXml(xmlFiles);
    }
}