package com.visioplanserver.repository;

import com.visioplanserver.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

        List<EmployeeEntity> getAllByUsername(String username);
}
