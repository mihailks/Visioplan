package com.visioplanserver.web;

import com.visioplanserver.model.view.FeedbackViewModel;
import com.visioplanserver.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FeedbackController {
    private final EmailService emailService;

    public FeedbackController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/feedback")
    public String feedback(Model model) {
        if (!model.containsAttribute("feedbackViewModel")){
            model.addAttribute("feedbackViewModel", new FeedbackViewModel());
        }
        return "feedback";
    }

    @PostMapping("/feedback")
    public String feedbackSubmit(@Valid FeedbackViewModel feedbackViewModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("feedbackViewModel", feedbackViewModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.feedbackViewModel", bindingResult);
            return "redirect:feedback";
        }

        emailService.sendFeedback(
                feedbackViewModel.getName(),
                feedbackViewModel.getEmail(),
                feedbackViewModel.getFeedback());

        return "redirect:/";
    }


}
