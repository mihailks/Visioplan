package com.visioplanserver.service.impl;

import com.visioplanserver.model.entity.CommentsEntity;
import com.visioplanserver.model.view.CommentsViewModel;
import com.visioplanserver.model.view.UserViewModel;
import com.visioplanserver.repository.CommentsRepository;
import com.visioplanserver.service.CommentsService;
import com.visioplanserver.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentsRepository commentsRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;


    public CommentsServiceImpl(CommentsRepository commentsRepository, ModelMapper modelMapper, UserService userService) {
        this.commentsRepository = commentsRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    public List<CommentsViewModel> getAllCommentsByFileId(Long fileId){
        return commentsRepository.getAllByFileId(fileId);
    }

    @Override
    public void addComment(Long id, String username) {
        UserViewModel userEntity = userService.findUserByUsername(username);
        CommentsEntity commentsEntity = new CommentsEntity();



    }


}
