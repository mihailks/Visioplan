package com.visioplanserver.web;


import com.visioplanserver.model.dto.CommentsAddDTO;
import com.visioplanserver.model.view.CommentsViewModel;
import com.visioplanserver.service.CommentsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

//@RestController
@Controller
public class CommentsController {
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/api/{fileId}/comments")
    public ResponseEntity<List<CommentsViewModel>> getComments(@PathVariable Long fileId) {
        return ResponseEntity.ok(commentsService.getAllCommentsByFileId(fileId));
    }

    @PostMapping("/comment/add/{id}/{buildingName}")
    public String addComment(@Valid CommentsAddDTO commentsAddDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @PathVariable Long id,
                             @PathVariable String buildingName,
                             Principal principal) {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("commentsAddDTO", commentsAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentsAddDTO", bindingResult);

            return "redirect:/file/{buildingName}";
        }

        commentsService.addComment(id, principal.getName(), commentsAddDTO);

        return "redirect:/file/{buildingName}";

    }


}
