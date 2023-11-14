package com.visioplanserver.service;

import com.visioplanserver.model.dto.CompanyRegistrationDTO;
import com.visioplanserver.model.entity.CompanyEntity;
import com.visioplanserver.model.view.CompanyNameViewModel;

import java.util.List;

public interface CompanyService {
    void register(CompanyRegistrationDTO companyRegistrationDTO);

    String getNameById(Long id);

    List<CompanyNameViewModel> getAllCompanies();

    CompanyEntity getCompanyByName(String companyName);
}
