package by.iharantanovich.thefirstserver.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileUploadService {

    public Map<String, String> readZip(MultipartFile file) {

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

        return xmlFiles;
    }
}