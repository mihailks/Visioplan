package com.visioplanserver.service.impl;

import com.visioplanserver.config.ReCaptchaConfig;
import com.visioplanserver.model.dto.RecaptchaResponseDTO;
import com.visioplanserver.service.RecaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Optional;
@Service
public class RecaptchaServiceImpl implements RecaptchaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecaptchaServiceImpl.class);
    private final WebClient webClient;
    private final ReCaptchaConfig recaptchaConfig;

    public RecaptchaServiceImpl(WebClient webClient, ReCaptchaConfig recaptchaConfig) {
        this.webClient = webClient;
        this.recaptchaConfig = recaptchaConfig;
    }

    @Override
    public Optional<RecaptchaResponseDTO> verify(String token) {
        RecaptchaResponseDTO response = webClient
                .post()
                .uri(this::buildRecaptchaUri)
                .body(BodyInserters
                        .fromFormData("secret", recaptchaConfig.getSecretKey())
                        .with("response", token))
                .retrieve()
                .bodyToMono(RecaptchaResponseDTO.class)
                .doOnError(t -> LOGGER.error("Error occurred while verifying reCAPTCHA token: {}", t.getMessage()))
                .onErrorComplete()
                .block();
        return Optional.ofNullable(response);
    }

    private URI buildRecaptchaUri(UriBuilder uriBuilder) {
        return uriBuilder
                .scheme("https")
                .host("www.google.com")
                .path("/recaptcha/api/siteverify")
                .build();
    }
}
