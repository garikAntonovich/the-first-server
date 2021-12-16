package by.iharantanovich.thefirstserver.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    void saveZippedFiles(MultipartFile file);

    void parseZippedFilesJAXB();

    void parseZippedFilesDom();

    void saveDataToTransfeFromJAXB();

    void saveDataToTransfeFromDom();

    void transferData();

    void processingFile(MultipartFile file);
}
