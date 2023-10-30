package com.visioplanserver.web;

import com.visioplanserver.model.dto.UserLoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    @ModelAttribute
    public UserLoginDTO userLoginDTO() {
        return UserLoginDTO.createEmpty();
    }

    @GetMapping("/login")
    public String login() {
        return "loginUser";
    }

//    @PostMapping("/login-error")
}
