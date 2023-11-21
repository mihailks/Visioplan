package com.visioplanserver.repository;

import com.visioplanserver.model.entity.CompanyEntity;
import com.visioplanserver.model.view.CompanyNameViewModel;
import com.visioplanserver.model.view.CompanyViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    Optional<CompanyEntity> findByName(String companyName);

    @Query("""
            SELECT distinct new com.visioplanserver.model.view.CompanyViewModel(
                c.id,
                c.name,
                c.address,
                c.city,
                c.country,
                c.phone,
                c.email,
                c.website
            )
            FROM CompanyEntity as c
            join c.employees as e
          
            """)
    List<CompanyViewModel> getAllCompaniesDetails();


    @Query("""
            SELECT new com.visioplanserver.model.view.CompanyNameViewModel(c.id ,c.name)
            FROM CompanyEntity c
            join c.employees as e
            WHERE e.username = :username
            """)
    CompanyNameViewModel findCompanyByEmployeeName(@Param("username") String username);
}
//@Query("""
//            SELECT new com.visioplanserver.model.view.FloorNameDTO(f.number)
//            FROM FloorEntity f
//            WHERE f.building.name = :buildingName
//            """)
