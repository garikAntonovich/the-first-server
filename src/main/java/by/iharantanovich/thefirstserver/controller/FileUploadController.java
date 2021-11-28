package by.iharantanovich.thefirstserver.controller;

import by.iharantanovich.thefirstserver.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Controller
public class FileUploadController {

    FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload_view";
    }

    @PostMapping("/upload-status")
    @ResponseBody
    public String uploadStatus(@RequestParam("file") MultipartFile file) {
        if (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".zip")) {
            fileUploadService.readZip(file);
            return file.getOriginalFilename() + " was uploaded successfully!";
        } else {
            return file.getOriginalFilename() + " not successfully upload...";
        }
    }
}
