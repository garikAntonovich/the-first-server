package by.iharantanovich.thefirstserver.controller;

import by.iharantanovich.thefirstserver.model.ZippedFile;
import by.iharantanovich.thefirstserver.service.ApiService;
import by.iharantanovich.thefirstserver.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Controller
public class FileUploadController {

    protected FileUploadService fileUploadService;
    protected ApiService apiService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService, ApiService apiService) {
        this.fileUploadService = fileUploadService;
        this.apiService = apiService;
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload_view";
    }

    @PostMapping("/upload")
    public String uploadStatus(@RequestParam("file") MultipartFile file, Model model) {

        if (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".zip")) {
            List<ZippedFile> zippedFileList = fileUploadService.saveZippedFiles(file);
            fileUploadService.parseZippedFiles(zippedFileList);
            apiService.postData(fileUploadService.extractData());
            model.addAttribute("message", "Successfully uploaded file: " + file.getOriginalFilename());
        } else {
            model.addAttribute("fail", "This is not a zip file: " + file.getOriginalFilename());
        }

        return "upload_status_view";
    }
}
