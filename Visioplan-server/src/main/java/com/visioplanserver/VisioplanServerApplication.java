package com.visioplanserver;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class VisioplanServerApplication {

    public static void main(String[] args) throws DbxException {
        SpringApplication.run(VisioplanServerApplication.class, args);
//        final String ACCESS_TOKEN = "sl.BpXppgUuSCQhaewPstH32Wx5QVbLPYp0Oa78mfF30vSVx0ByZrP9wXruHdrnlHQS8XALGTJv24LsFrf4vVMxY9pJe0u34LQxiDPIFyGvkq8J69n5q11uj04qBlyvHzTedS391RqnxzfL";
//
//
//        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
//        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
//
//        FullAccount account = client.users().getCurrentAccount();
//        System.out.println(account.getName().getDisplayName());

    }

}
