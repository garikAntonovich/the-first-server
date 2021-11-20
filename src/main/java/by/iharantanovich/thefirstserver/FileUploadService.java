package by.iharantanovich.thefirstserver;

import org.springframework.web.multipart.MultipartFile;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUploadService {

    public static void method(MultipartFile file) {

        try (ZipInputStream zin = new ZipInputStream(file.getInputStream())) {
            ZipEntry entry;
            String name;
            long size;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                size = entry.getSize();
                System.out.printf("File name: %s \t File size: %d \n", name, size);
                zin.closeEntry();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(file.getOriginalFilename());
    }
}
