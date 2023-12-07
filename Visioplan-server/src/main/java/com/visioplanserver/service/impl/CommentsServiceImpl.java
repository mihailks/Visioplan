package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.CommentsAddDTO;
import com.visioplanserver.model.entity.CommentsEntity;
import com.visioplanserver.model.entity.FileEntity;
import com.visioplanserver.model.entity.UserEntity;
import com.visioplanserver.model.view.CommentsViewModel;
import com.visioplanserver.repository.CommentsRepository;
import com.visioplanserver.service.CommentsService;
import com.visioplanserver.service.EmailService;
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
    private final UserService userService;
    private final FileService fileService;
    private final EmailService emailService;


    public CommentsServiceImpl(CommentsRepository commentsRepository, UserService userService, FileService fileService, EmailService emailService) {
        this.commentsRepository = commentsRepository;
        this.userService = userService;
        this.fileService = fileService;
        this.emailService = emailService;
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

    @Override
    public void sendNewCommentsEmail() {
        List<CommentsViewModel> commentsEntities = commentsRepository.findAllByCreatedBefore(LocalDateTime.now().minusDays(1));
        emailService.sendNewCommentsEmail(commentsEntities);
    }


}
