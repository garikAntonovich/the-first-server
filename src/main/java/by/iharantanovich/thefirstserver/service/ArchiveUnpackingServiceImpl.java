package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.ZippedFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ArchiveUnpackingServiceImpl implements ArchiveUnpackingService {

    @Override
    public List<ZippedFile> unpackArchive(MultipartFile file) {

        List<ZippedFile> zippedFileList = new ArrayList<>();

        try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {

            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".xml")) {
                    zippedFileList.add(new ZippedFile(zipEntry.getName(), new String(zis.readNBytes((int) zipEntry.getSize()))));
                } else {
                    continue;
                }
                zis.closeEntry();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return zippedFileList;
    }
}
