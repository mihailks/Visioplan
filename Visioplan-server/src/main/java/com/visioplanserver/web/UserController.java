package com.visioplanserver.web;

import com.visioplanserver.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}

