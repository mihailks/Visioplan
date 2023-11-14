package com.visioplanserver.web;

import com.dropbox.core.DbxException;
import com.visioplanserver.model.dto.AddFileDTO;
import com.visioplanserver.model.view.FileViewModel;
import com.visioplanserver.service.BuildingService;
import com.visioplanserver.service.FileService;
import com.visioplanserver.service.FloorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
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
// old getAllFiles
//    @GetMapping("/all")
//    public String allFiles(Model model,
//                           @PageableDefault(size = 10)
//                           Pageable pageable) {
//        List<FileViewModel> files = fileService.getAllFiles(); // all files, no pagination
//        Page<FileViewModel> allFiles = fileService.getAllFiles(pageable); // no pagination, 10 files per page
//
//        model.addAttribute("files", allFiles);
//        return "all-files";
//    }

    @GetMapping("/all")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/all/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<FileViewModel> page = fileService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        Long totalFiles = page.getTotalElements();
        List<FileViewModel> files = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalFiles", totalFiles);
        model.addAttribute("files", files);
        return "all-files";
    }

    @GetMapping("/add")
    public String allProjects(Model model) {
        if (!model.containsAttribute("addFileDTO")) {
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
    public String delete(@PathVariable("id") Long id) {
        fileService.deleteFile(id);
        return "redirect:/file/all";
    }
}
