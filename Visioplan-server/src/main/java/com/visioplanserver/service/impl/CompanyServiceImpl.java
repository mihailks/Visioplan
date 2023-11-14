package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.CompanyRegistrationDTO;
import com.visioplanserver.model.entity.CompanyEntity;
import com.visioplanserver.model.view.CompanyNameViewModel;
import com.visioplanserver.repository.CompanyRepository;
import com.visioplanserver.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public String getNameById(Long id) {
        CompanyEntity company = companyRepository.findById(id).orElse(null);
        return company.getName();
    }

    @Override
    public List<CompanyNameViewModel> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(companyEntity -> modelMapper.map(companyEntity, CompanyNameViewModel.class))
                .toList();
    }

    @Override
    public CompanyEntity getCompanyByName(String companyName) {
        return companyRepository.findByName(companyName).orElseThrow(()-> new RuntimeException("Company not found"));
    }
}
