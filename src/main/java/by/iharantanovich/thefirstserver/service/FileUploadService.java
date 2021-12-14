package by.iharantanovich.thefirstserver.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    void saveZippedFiles(MultipartFile file);

    void parseZippedFiles();

    void extractData();

    void transferData();

    void processingFile(MultipartFile file);
}
