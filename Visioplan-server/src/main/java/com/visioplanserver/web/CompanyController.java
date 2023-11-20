package com.visioplanserver.web;

import com.visioplanserver.model.dto.CompanyRegistrationDTO;
import com.visioplanserver.model.view.CompanyNameViewModel;
import com.visioplanserver.model.view.CompanyViewModel;
import com.visioplanserver.service.CompanyService;
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
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/register")
    public String register(Model model) {

        if(!model.containsAttribute("companyRegistrationDTO")){
            model.addAttribute("companyRegistrationDTO", CompanyRegistrationDTO.createEmpty());
        }

        return "company-register";
    }

    @PostMapping("/register")
    public String register(@Valid CompanyRegistrationDTO companyRegistrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.companyRegistrationDTO", bindingResult);
            redirectAttributes.addFlashAttribute("companyRegistrationDTO", companyRegistrationDTO);
            return "redirect:/company/register";
        }

        companyService.register(companyRegistrationDTO);
        return "redirect:/";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        List<CompanyViewModel> allCompanies = companyService.getAllCompaniesDetails();
        model.addAttribute("companies", allCompanies);
        return "companies-all";
    }



}
