package com.visioplanserver.web;

import com.visioplanserver.entity.EmployeeEntity;
import com.visioplanserver.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class testRestApiController {
    private final EmployeeRepository userRepository;

    public testRestApiController(EmployeeRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/api/test")
    public List<EmployeeEntity> getAllUsers() {
        String username = "misho";
        List<EmployeeEntity> users =  userRepository.getAllByUsername(username);
        return users;
    }


}
