package by.iharantanovich.thefirstserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @GetMapping("/upload-view")
    public String uploadPage() {
        return "upload_view";
    }

    @PostMapping("/upload-status")
    @ResponseBody
    public String uploadStatus(@RequestParam("file") MultipartFile file) {
        return file.getOriginalFilename();
    }
}
