package com.visioplanserver.web;

import com.dropbox.core.DbxException;
import com.visioplanserver.model.dto.AddFileDTO;
import com.visioplanserver.model.view.FileViewModel;
import com.visioplanserver.service.BuildingService;
import com.visioplanserver.service.FileService;
import com.visioplanserver.service.FloorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;
    private final BuildingService buildingService;
    private final FloorService floorService;


    public FileController(FileService fileService, BuildingService buildingService, FloorService floorService) {
        this.fileService = fileService;
        this.buildingService = buildingService;
        this.floorService = floorService;
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

        List<String> testPrint = List.of(new String[]{"test1", "test2", "test3", "test4"});
        model.addAttribute("buildings", buildingService.getAllBuildingsNames());
        model.addAttribute("testPrint", "testPrint");
        return "file-upload";
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

        return "redirect:/file/all";
    }

    @GetMapping("/dropbox")
    public String dropbox(Model model) throws DbxException {
//        String toPrint = dropboxService.getAccountDetails();
//        model.addAttribute("files", toPrint);
//
//        dropboxService.getFileNames();

        return "dropbox-test";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        fileService.deleteFile(id);
        return "redirect:/file/all";
    }
}
