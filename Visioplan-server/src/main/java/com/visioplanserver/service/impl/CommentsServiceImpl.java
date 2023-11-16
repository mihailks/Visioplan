package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.CommentsAddDTO;
import com.visioplanserver.model.entity.CommentsEntity;
import com.visioplanserver.model.entity.FileEntity;
import com.visioplanserver.model.entity.UserEntity;
import com.visioplanserver.model.view.CommentsViewModel;
import com.visioplanserver.repository.CommentsRepository;
import com.visioplanserver.service.CommentsService;
import com.visioplanserver.service.FileService;
import com.visioplanserver.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentsRepository commentsRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final FileService fileService;


    public CommentsServiceImpl(CommentsRepository commentsRepository, ModelMapper modelMapper, UserService userService, FileService fileService) {
        this.commentsRepository = commentsRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.fileService = fileService;
    }


    public List<CommentsViewModel> getAllCommentsByFileId(Long fileId) {
        return commentsRepository.getAllByFileId(fileId);
    }

    @Transactional
    @Override
    public void addComment(Long id, String username, CommentsAddDTO commentsAddDTO) {
        FileEntity file = fileService.findById(id);
        UserEntity userEntity = userService.findUserByUsernameEntity(username);
        CommentsEntity commentsEntity = new CommentsEntity();
        commentsEntity.setAuthor(userEntity)
                .setFile(file)
                .setCreated(LocalDateTime.now())
                .setTextContent(commentsAddDTO.getTextContent());
        commentsRepository.save(commentsEntity);
    }


}
