package com.visioplanserver.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedbackController {
    @GetMapping("/feedback")
    public String feedback() {
        return "feedback";
    }
}
