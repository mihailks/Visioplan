package com.visioplanserver.service;

import com.visioplanserver.model.dto.CommentsAddDTO;
import com.visioplanserver.model.view.CommentsViewModel;

import java.util.List;

public interface CommentsService {
    List<CommentsViewModel> getAllCommentsByFileId(Long fileId);


    void addComment(Long id, String username, CommentsAddDTO commentsAddDTO);
}
