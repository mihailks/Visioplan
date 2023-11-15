package com.visioplanserver.web;


import com.visioplanserver.model.view.CommentsViewModel;
import com.visioplanserver.service.CommentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class CommentsController {
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/api/{fileId}/comments")
        public ResponseEntity<List<CommentsViewModel>> getComments(@PathVariable Long fileId) {
        return ResponseEntity.ok(commentsService.getAllCommentsByFileId(fileId));
    }

    @PostMapping("/comments/add/{id}")
    public String addComment(@PathVariable Long id, Principal principal) {
        commentsService.addComment(id, principal.getName());
        return "redirect:/file/all";
    }




}
