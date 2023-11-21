package com.visioplanserver.web;

import com.visioplanserver.model.view.BuildingViewModel;
import com.visioplanserver.service.BuildingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
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

    @GetMapping("/home")
    public String homePage(Model model, Principal principal, @AuthenticationPrincipal UserDetails viewer) {
        if (viewer.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("stats", buildingService.getStats());
            return "admin-panel";
        }

        List<BuildingViewModel> buildings = buildingService.getAllBuildingsByCompanyName(principal.getName());
        model.addAttribute("buildings", buildings);
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/picture")
    public String picture() {
        return "bulding-add";
    }

    @GetMapping("/test-side-bar")
    public String testForm() {
        return "test-side-bar";
    }
}
