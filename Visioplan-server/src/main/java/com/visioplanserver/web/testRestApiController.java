package com.visioplanserver.web;

import com.visioplanserver.repository.UserRepository;

public class testRestApiController {
    private final UserRepository userRepository;

    public testRestApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//    @GetMapping("/api/test")
//    public List<EmployeeEntity> getAllUsers() {
//        String username = "misho";
//        List<EmployeeEntity> users =  userRepository.getAllByUsername(username);
//        return users;
//    }


}
