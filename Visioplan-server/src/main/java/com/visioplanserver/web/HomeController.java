package com.visioplanserver.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/picture")
    public String picture() {
        return "testAddBuildingUploadPic";
    }

    @GetMapping("/testForm")
    public String testForm() {
        return "testForm";
    }
}
