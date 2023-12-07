package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.CompanyRegistrationDTO;
import com.visioplanserver.model.entity.CompanyEntity;
import com.visioplanserver.model.entity.FileEntity;
import com.visioplanserver.model.view.CompanyNameViewModel;
import com.visioplanserver.model.view.CompanyViewModel;
import com.visioplanserver.model.view.FileViewModel;
import com.visioplanserver.repository.CompanyRepository;
import com.visioplanserver.service.exeption.CompanyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static com.visioplanserver.testUtils.CreateTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {
    CompanyServiceImpl companyService;
    @Mock
    private CompanyRepository mockCompanyRepository;
    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    void setUp() {
        companyService = new CompanyServiceImpl(mockCompanyRepository, mockModelMapper);
    }

    @Test
    void register() {
        CompanyRegistrationDTO companyRegistrationDTO = createTestCompanyRegistrationDTO();
        companyService.register(companyRegistrationDTO);
        verify(mockCompanyRepository).save(any());
    }

    @Test
    void getNameById() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(1L);
        companyEntity.setName("testName");
        when(mockCompanyRepository.findById(1L)).thenReturn(java.util.Optional.of(companyEntity));
        String nameById = companyService.getNameById(1L);
        assertEquals("testName", nameById);
    }

    @Test
    void getNameByIdShouldThrow() {
        when(mockCompanyRepository.findById(1L))
                .thenReturn(java.util.Optional.empty());
        assertThrows(CompanyNotFoundException.class,
                () -> companyService.getNameById(1L));
    }

    @Test
    void getAllCompanies() {
        List<CompanyEntity> companies = new ArrayList<>();
        companies.add(createTestCompany());

        when(mockCompanyRepository.findAll()).thenReturn(companies);
        when(mockModelMapper.map(any(CompanyEntity.class), any()))
                .thenReturn(createTestCompanyNameViewModel());

        List<CompanyNameViewModel> allCompanies = companyService.getAllCompanies();

        assertEquals(1, allCompanies.size());
        assertEquals(allCompanies.get(0).getName(), companies.get(0).getName());

    }


    @Test
    void getCompanyByName() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(1L);
        companyEntity.setName("testName");
        when(mockCompanyRepository.findByName("testName"))
                .thenReturn(java.util.Optional.of(companyEntity));
        CompanyEntity companyByName = companyService.getCompanyByName("testName");
        assertEquals("testName", companyByName.getName());
    }

    @Test
    void getCompanyByNameShouldThrow() {
        when(mockCompanyRepository.findByName("testName"))
                .thenReturn(java.util.Optional.empty());
        assertThrows(CompanyNotFoundException.class,
                () -> companyService.getCompanyByName("testName"));
    }

    @Test
    void findCompanyByEmployeeName() {
        CompanyNameViewModel companyNameViewModel = new CompanyNameViewModel();
        companyNameViewModel.setName("testName");
        when(mockCompanyRepository.findCompanyByEmployeeName("testUsername"))
                .thenReturn(companyNameViewModel);
        CompanyNameViewModel companyByEmployeeName = companyService.findCompanyByEmployeeName("testUsername");
        assertEquals("testName", companyByEmployeeName.getName());

    }

    @Test
    void findPage() {
        List<CompanyEntity> companies = new ArrayList<>();
        companies.add(createTestCompany());
        Page<CompanyEntity> page = new PageImpl<>(companies);

        CompanyViewModel companyViewModel = createTestCompanyViewModel();
        when(mockModelMapper.map(any(CompanyEntity.class), any()))
                .thenReturn(companyViewModel);
        when(mockCompanyRepository.findAll(any(PageRequest.class)))
                .thenReturn(page);

        Page<CompanyViewModel> result = companyService.findPage(1);

        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getContent().size());

    }

}
