package com.visioplanserver.repository;

import com.visioplanserver.model.entity.BuildingEntity;
import com.visioplanserver.model.view.BuildingViewModel;
import com.visioplanserver.model.view.StatsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {

    Optional<BuildingEntity> findByName(String buildingName);


    @Query("""
    SELECT DISTINCT new com.visioplanserver.model.view.BuildingViewModel(b.id, b.name, b.address, b.city, b.country, b.imgUrl)
    FROM BuildingEntity b
     join b.companies c
    where c.name = :companyName
""")
    List<BuildingViewModel> findAllCompaniesByCompanyName(@Param("companyName") String companyName);


    @Query("""
    SELECT new com.visioplanserver.model.view.StatsViewModel(
        (SELECT COUNT(b) FROM BuildingEntity b),
        (SELECT COUNT(c) FROM CompanyEntity c),
        (SELECT COUNT(u) FROM UserEntity u),
        (SELECT COUNT(f) FROM FileEntity f)
    )
    """)
    StatsViewModel getTotalEntriesCount();
}
