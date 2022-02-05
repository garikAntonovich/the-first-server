package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.ZippedFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArchiveUnpackingService extends FileUploadService {

    List<ZippedFile> unpackArchive(MultipartFile file);
}
