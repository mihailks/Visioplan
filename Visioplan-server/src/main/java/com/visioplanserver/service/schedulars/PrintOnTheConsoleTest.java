package com.visioplanserver.service.schedulars;

import com.visioplanserver.service.CommentsService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class PrintOnTheConsoleTest {
    private CommentsService commentsService;

    public PrintOnTheConsoleTest(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

//    @Scheduled(fixedRate = 10000)
//    public void printOnTheConsole() {
//        System.out.println("Print on the console");
//    }
//
//    @Scheduled(cron = "0 0 6 * * ?")
//    public void sendNewCommentsEmail() {
//       commentsService.sendNewCommentsEmail();
//    }

//    @Scheduled(fixedRate = 1000000)
//    public void testNewCommentsSend() {
//        commentsService.sendNewCommentsEmail();
//    }

}
