package com.visioplanserver.web;

import com.visioplanserver.model.dto.CompanyRegistrationDTO;
import com.visioplanserver.model.view.CompanyNameViewModel;
import com.visioplanserver.model.view.CompanyViewModel;
import com.visioplanserver.model.view.FileViewModel;
import com.visioplanserver.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        if (!model.containsAttribute("companyRegistrationDTO")) {
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
        return "redirect:home";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/all/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<CompanyViewModel> page = companyService.findPage(currentPage);
        pageDetails(model, currentPage, page);
        return "companies-all";
    }

    private void pageDetails(Model model, @PathVariable("pageNumber") int currentPage, Page<CompanyViewModel> page) {
        int totalPages = page.getTotalPages();
        Long totalFiles = page.getTotalElements();
        List<CompanyViewModel> companies = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalCompanies", totalFiles);
        model.addAttribute("companies", companies);
    }
}
