package by.iharantanovich.thefirstserver.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileUploadService {

    public List<String> readZip(MultipartFile file) {

        List<String> xmlFiles = new ArrayList<>();

        try {
            ZipInputStream zis = new ZipInputStream(file.getInputStream());
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                byte[] bytes = zis.readNBytes((int) zipEntry.getSize());
                xmlFiles.add(new String(bytes));
                zis.closeEntry();
            }
            zis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return xmlFiles;
    }
}
