package com.visioplanserver.web;

import com.visioplanserver.model.view.FileViewModel;
import com.visioplanserver.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/all")
    public String allFiles(Model model) {
        List<FileViewModel> files = fileService.getAllFiles();
        model.addAttribute("files", files);
        return "all-files";
    }
}
