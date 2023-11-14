package com.visioplanserver.web;

import com.visioplanserver.model.dto.UserProfileEditDTO;
import com.visioplanserver.model.view.UserViewModel;
import com.visioplanserver.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByUsername(principal.getName()));

        return "profile-view";
    }


    @GetMapping("/profile/edit")
    public String editProfile(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByUsername(principal.getName()));
        return "profile-edit";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@Valid UserProfileEditDTO userProfileEditDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userProfileEditDTO", userProfileEditDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userProfileEditDTO", bindingResult);
            return "redirect:profile-edit";
        }

        userService.updateUserProfile(userProfileEditDTO);


        return "redirect:/profile";
    }



}

