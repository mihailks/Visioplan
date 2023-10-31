package com.visioplanserver.web;

import com.visioplanserver.model.view.ProjectViewModel;
import com.visioplanserver.service.BuildingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final BuildingService buildingService;

    public HomeController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/project/all")
    public String project(Model model) {
        List<ProjectViewModel> buildings = buildingService.getAllBuildings();
        model.addAttribute("buildings", buildings);
        return "allProjects";
    }
}
