package com.visioplanserver.web;

import com.visioplanserver.model.dto.AddBuildingDTO;
import com.visioplanserver.model.view.BuildingViewModel;
import com.visioplanserver.service.BuildingService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/building")
public class BuildingController {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/add")
    public String allProjects(Model model) {
        if (!model.containsAttribute("addBuildingDTO")) {
            model.addAttribute("addBuildingDTO", AddBuildingDTO.createEmpty());
        }
        return "building-add";
    }

    @GetMapping("/all")
    public String project(Model model) {
        List<BuildingViewModel> buildings = buildingService.getAllBuildings();
        model.addAttribute("buildings", buildings);
        return "buildings-all";
    }


    @PostMapping("/add")
    public String addBuilding(@Valid AddBuildingDTO addBuildingDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addBuildingDTO", addBuildingDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addBuildingDTO", bindingResult);
            return "redirect:add";
        }

        buildingService.addNewBuilding(addBuildingDTO);

        return "redirect:/building/all";
    }
}
