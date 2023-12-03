package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.CompanyRegistrationDTO;
import com.visioplanserver.model.entity.CompanyEntity;
import com.visioplanserver.model.view.CompanyNameViewModel;
import com.visioplanserver.model.view.CompanyViewModel;
import com.visioplanserver.repository.CompanyRepository;
import com.visioplanserver.service.CompanyService;
import com.visioplanserver.service.exeption.CompanyNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        CompanyEntity company = companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
        return company.getName();
    }

    @Override
    public List<CompanyNameViewModel> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(companyEntity -> modelMapper.map(companyEntity, CompanyNameViewModel.class))
                .toList();
    }

    @Override
    public List<CompanyViewModel> getAllCompaniesDetails() {
        List<CompanyViewModel> companiesDetails = companyRepository.getAllCompaniesDetails();
        return companiesDetails;
    }

    @Override
    public CompanyEntity getCompanyByName(String companyName) {
        return companyRepository.findByName(companyName).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
    }

    @Override
    public CompanyNameViewModel findCompanyByEmployeeName(String username) {
        return companyRepository.findCompanyByEmployeeName(username);
    }

    @Override
    public Page<CompanyViewModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10);
        Page<CompanyViewModel> companiesDetails = companyRepository.getAllCompaniesDetails(pageable);
        return companiesDetails;
    }
}
