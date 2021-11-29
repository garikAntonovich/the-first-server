package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.ZippedFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileUploadService {

    List<ZippedFile> saveZippedFiles(MultipartFile file);

    Map<String, String> getZippedFiles(List<ZippedFile> zippedFileList);

    void parseZippedFiles(Map<String, String> zippedFilesMap);

}
