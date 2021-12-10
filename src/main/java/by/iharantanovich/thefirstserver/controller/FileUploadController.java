package by.iharantanovich.thefirstserver.controller;

import by.iharantanovich.thefirstserver.model.ZippedFile;
import by.iharantanovich.thefirstserver.service.DataTransferService;
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
    protected DataTransferService dataTransferService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService, DataTransferService dataTransferService) {
        this.fileUploadService = fileUploadService;
        this.dataTransferService = dataTransferService;
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
            dataTransferService.transferData(fileUploadService.extractData());
            model.addAttribute("message", "Successfully uploaded file: " + file.getOriginalFilename());
        } else {
            model.addAttribute("fail", "This is not a zip file: " + file.getOriginalFilename());
        }

        return "upload_status_view";
    }
}
