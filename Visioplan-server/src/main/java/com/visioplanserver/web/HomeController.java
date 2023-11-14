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

    @GetMapping("/test-side-bar")
    public String testForm() {
        return "test-side-bar";
    }
}
