package com.visioplanserver.repository;

import com.visioplanserver.model.entity.CommentsEntity;
import com.visioplanserver.model.view.CommentsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {


//    @Query("SELECT c FROM CommentsEntity c WHERE c.file.id = :fileId")
@Query("SELECT new com.visioplanserver.model.view.CommentsViewModel(c.id, c.textContent, c.author.username, c.file.id, c.created) " +
        "FROM CommentsEntity c WHERE c.file.id = :fileId")
List<CommentsViewModel> getAllByFileId(Long fileId);
}
