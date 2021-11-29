package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.ExtractedInformation;
import by.iharantanovich.thefirstserver.model.ZippedFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    List<ZippedFile> saveZippedFiles(MultipartFile file);

    void parseZippedFiles(List<ZippedFile> zippedFiles);

    List<ExtractedInformation> extractingData();

}
