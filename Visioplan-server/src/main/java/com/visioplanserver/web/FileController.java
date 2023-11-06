package com.visioplanserver.web;

import com.dropbox.core.DbxException;
import com.visioplanserver.model.dto.AddBuildingDTO;
import com.visioplanserver.model.dto.AddFileDTO;
import com.visioplanserver.model.dto.TESTAddFileDTO;
import com.visioplanserver.model.view.FileViewModel;
import com.visioplanserver.service.DropboxService;
import com.visioplanserver.service.FileService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;
    private final DropboxService dropboxService;

    public FileController(FileService fileService, DropboxService dropboxService) {
        this.fileService = fileService;
        this.dropboxService = dropboxService;
    }

    @GetMapping("/all")
    public String allFiles(Model model) {
        List<FileViewModel> files = fileService.getAllFiles();
        model.addAttribute("files", files);
        return "all-files";
    }

    @GetMapping("/add")
    public String allProjects(Model model) {
        if (!model.containsAttribute("addFileDTO")){
            model.addAttribute("addFileDTO", AddFileDTO.createEmpty());
        }
        return "fileUploadTest";
    }

    @PostMapping("/add")
    public String addFile(@Valid AddFileDTO addFileDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addFileDTO", addFileDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addFileDTO", bindingResult);
            return "redirect:add";
        }

        fileService.addNewFile(addFileDTO);

        return "redirect:/dropbox-test";
    }

    @GetMapping("/dropbox")
    public String dropbox(Model model) throws DbxException {
        String toPrint = dropboxService.getAccountDetails();
        model.addAttribute("files", toPrint);

        dropboxService.getFileNames();

        return "dropbox-test";
    }
}
