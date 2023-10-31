package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.CompanyRegistrationDTO;
import com.visioplanserver.model.entity.CompanyEntity;
import com.visioplanserver.repository.CompanyRepository;
import com.visioplanserver.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void register(CompanyRegistrationDTO companyRegistrationDTO) {


        companyRepository.save(modelMapper.map(companyRegistrationDTO, CompanyEntity.class));
    }
}
