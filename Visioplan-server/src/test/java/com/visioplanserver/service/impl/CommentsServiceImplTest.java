package com.visioplanserver.service.impl;

import com.visioplanserver.model.view.CommentsViewModel;
import com.visioplanserver.repository.CommentsRepository;
import com.visioplanserver.service.EmailService;
import com.visioplanserver.service.FileService;
import com.visioplanserver.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static com.visioplanserver.testUtils.CreateTestData.createTestCommentsAddDTO;
import static com.visioplanserver.testUtils.CreateTestData.createTestCommentsViewModel;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentsServiceImplTest {
    CommentsServiceImpl commentsService;
    @Mock
    private CommentsRepository mockCommentsRepository;
    @Mock
    private UserService mockUserService;
    @Mock
    private FileService mockFileService;
    @Mock
    private EmailService mockEmailService;
@BeforeEach
    void setUp() {
        commentsService = new CommentsServiceImpl(mockCommentsRepository, mockUserService, mockFileService, mockEmailService);
    }

    @Test
    void getAllCommentsByFileId() {
        List<CommentsViewModel> comments = commentsService.getAllCommentsByFileId(1L);
        comments.add(createTestCommentsViewModel());
        when(mockCommentsRepository.getAllByFileId(1L)).thenReturn(comments);
        List<CommentsViewModel> allCommentsByFileId = commentsService.getAllCommentsByFileId(1L);
        assertEquals(1, allCommentsByFileId.size());
        assertEquals(allCommentsByFileId.get(0).getAuthor(), comments.get(0).getAuthor());
        assertEquals(allCommentsByFileId.get(0).getCreated(), comments.get(0).getCreated());
        assertEquals(allCommentsByFileId.get(0).getTextContent(), comments.get(0).getTextContent());
        assertEquals(allCommentsByFileId.get(0).getAuthor(), comments.get(0).getAuthor());
        assertEquals(allCommentsByFileId.get(0).getFile(), comments.get(0).getFile());
    }

    @Test
    void addComment() {
        commentsService.addComment(1L, "testUsername", createTestCommentsAddDTO());
        verify(mockCommentsRepository).save(any());
    }

    @Test
    void sendNewCommentsEmail() {
        List<CommentsViewModel> comments = commentsService.getAllCommentsByFileId(1L);
        comments.add(createTestCommentsViewModel());
        when(mockCommentsRepository.findAllByCreatedBefore(any())).thenReturn(comments);
        commentsService.sendNewCommentsEmail();
        verify(mockEmailService).sendNewCommentsEmail(comments);
    }



}
