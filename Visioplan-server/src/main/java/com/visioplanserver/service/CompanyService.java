package com.visioplanserver.service;

import com.visioplanserver.model.dto.CompanyRegistrationDTO;

public interface CompanyService {
    void register(CompanyRegistrationDTO companyRegistrationDTO);

    String getNameById(Long id);
}
