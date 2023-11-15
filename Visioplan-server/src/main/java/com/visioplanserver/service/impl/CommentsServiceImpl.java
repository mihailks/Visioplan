package com.visioplanserver.service.impl;

import com.visioplanserver.model.view.CommentsViewModel;
import com.visioplanserver.repository.CommentsRepository;
import com.visioplanserver.service.CommentsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentsRepository commentsRepository;

    public CommentsServiceImpl(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }


    public List<CommentsViewModel> getAllCommentsByFileId(Long fileId){
        return commentsRepository.getAllByFileId(fileId);
    }
}
