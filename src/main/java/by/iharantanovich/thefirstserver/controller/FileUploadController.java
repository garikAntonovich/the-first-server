package by.iharantanovich.thefirstserver.controller;

import by.iharantanovich.thefirstserver.model.ZippedFile;
import by.iharantanovich.thefirstserver.service.FileUploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class FileUploadController {

    protected FileUploadServiceImpl fileUploadService;

    @Autowired
    public FileUploadController(FileUploadServiceImpl fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload_view";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String uploadStatus(@RequestParam("file") MultipartFile file) {
        if (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".zip")) {
            List<ZippedFile> zippedFileList = fileUploadService.saveZippedFiles(file);
            Map<String, String> zippedFiles = fileUploadService.getZippedFiles(zippedFileList);
            fileUploadService.parseZippedFiles(zippedFiles);

            return file.getOriginalFilename() + " was uploaded successfully!";
        } else {
            return file.getOriginalFilename() + " not successfully upload...";
        }
    }
}
