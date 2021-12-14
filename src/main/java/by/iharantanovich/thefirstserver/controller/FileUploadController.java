package by.iharantanovich.thefirstserver.controller;

import by.iharantanovich.thefirstserver.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload_view";
    }

    @PostMapping("/upload")
    public String uploadStatus(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("fail", "File is empty...");
        } else {
            model.addAttribute("message", "Successfully uploaded file: " + file.getOriginalFilename());
            fileUploadService.processingFile(file);
        }
        return "upload_status_view";
    }
}
