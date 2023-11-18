package com.visioplanserver.web;

import com.visioplanserver.model.dto.UserRegistrationDTO;
import com.visioplanserver.service.CompanyService;
import com.visioplanserver.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserRegisterController {
    private final UserService userService;
    private final CompanyService companyService;

    public UserRegisterController(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegistrationDTO")){
            model.addAttribute("userRegistrationDTO", UserRegistrationDTO.createEmpty());
        }
        model.addAttribute("companies", companyService.getAllCompanies());
        return "user-register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegistrationDTO userRegistrationDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
            return "redirect:register";
        }
        userService.registerUser(userRegistrationDTO);

        return "redirect:login";
    }
}
