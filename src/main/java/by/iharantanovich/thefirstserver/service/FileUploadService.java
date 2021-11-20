package by.iharantanovich.thefirstserver.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileUploadService {

    public void readZip(MultipartFile file) {

        try (ZipInputStream zin = new ZipInputStream(file.getInputStream())) {
            ZipEntry entry;
            String name;
            long size;
            System.out.println(file.getOriginalFilename());
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                size = entry.getSize();
                System.out.printf("File name: %s \t File size: %d \n", name, size);
                zin.closeEntry();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
