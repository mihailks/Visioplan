package com.visioplanserver.repository;

import com.visioplanserver.model.entity.FileEntity;
import com.visioplanserver.model.view.FileViewModel;
import com.visioplanserver.model.view.temp.FileViewModelByBuildingName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

    @Query(value = """
            SELECT f.*, COUNT(c.id)
        FROM visiodb.files as f
                 JOIN visiodb.comments as c on f.id = c.id
        group by f.id;
            """, nativeQuery = true)
    List<FileEntity> getAllFilesAndCommentsCounter();


//    @Query("select f from FileEntity f where f.floor.building.company.name = :companyName")

//    List<FileViewModelByBuildingName> findAllByFloor_Building_Name(String buildingName);
    Page<FileEntity> findAllByFloor_Building_Name(String buildingName, Pageable pageable);




//    @Query("""
//            select new com.visioplanserver.model.view.FileViewModel(
//            f.name,
//            f.url,
//            f.uploadDate,
//            f.floor,
//            f.extension,
//            f.part,
//            COUNT(c.id))
//            from FileEntity f
//            join f.comments c
//            group by f.id
//            """)
//    List<FileViewModel> getAllFilesAndCommentsCounter();


}

