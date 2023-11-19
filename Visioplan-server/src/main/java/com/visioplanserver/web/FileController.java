package com.visioplanserver.web;

import com.visioplanserver.model.dto.AddFileDTO;
import com.visioplanserver.model.view.CommentsViewModel;
import com.visioplanserver.model.view.FileViewModel;
import com.visioplanserver.service.BuildingService;
import com.visioplanserver.service.FileService;
import com.visioplanserver.service.FloorService;
import com.visioplanserver.service.exeption.FileNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
    public String getAllPages(Model model) {
        return getOnePage(model, 1);
    }


    @GetMapping("/all/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<FileViewModel> page = fileService.findPage(currentPage);
        pageDetails(model, currentPage, page);
        return "files-all";
    }


    @GetMapping("/{buildingName}")
    public String getAllPages(Model model, @PathVariable("buildingName") String buildingName) {
        return getOnePage(model, 1, buildingName);
    }

    @GetMapping("/{buildingName}/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, @PathVariable("buildingName") String buildingName) {
        Page<FileViewModel> page = fileService.findPage(currentPage, buildingName);
        model.addAttribute("buildingName", buildingName);
        pageDetails(model, currentPage, page);
        return "files-all-per-building";
    }


    private void pageDetails(Model model, @PathVariable("pageNumber") int currentPage, Page<FileViewModel> page) {
        int totalPages = page.getTotalPages();
        Long totalFiles = page.getTotalElements();
        List<FileViewModel> files = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalFiles", totalFiles);
        model.addAttribute("files", files);
    }


    @GetMapping("/add")
    public String allProjects(Model model) {
        if (!model.containsAttribute("addFileDTO")) {
            model.addAttribute("addFileDTO", AddFileDTO.createEmpty());
        }

        model.addAttribute("buildings", buildingService.getAllBuildingsNames());
        model.addAttribute("testPrint", "testPrint");
        return "file-upload";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("comemnts", new CommentsViewModel());
    }

    @PostMapping("/add")
    public String addFile(@Valid AddFileDTO addFileDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          @AuthenticationPrincipal UserDetails uploader) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addFileDTO", addFileDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addFileDTO", bindingResult);
            return "redirect:add";
        }

        fileService.addNewFile(addFileDTO, uploader);

        return "redirect:/file/all";
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        fileService.deleteFile(id);
        return "redirect:/file/all";
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ModelAndView handleNotFound(FileNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("error/file-not-found");
        modelAndView.addObject("id", exception.getMessage());
        return modelAndView;
    }
}
