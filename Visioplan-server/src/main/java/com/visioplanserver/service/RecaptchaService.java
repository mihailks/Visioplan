package com.visioplanserver.service;


import com.visioplanserver.model.dto.RecaptchaResponseDTO;

import java.util.Optional;

public interface RecaptchaService {
    Optional<RecaptchaResponseDTO> verify(String token);
}
