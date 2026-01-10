package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.WebauthnCredentialDTO;
import com.unnathy.fieldwise.service.WebauthnCredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webauthnCredentials")
@RequiredArgsConstructor
public class WebauthnCredentialController implements BaseController<WebauthnCredentialDTO, Long> {

    private final WebauthnCredentialService service;

    @Override
    public BasicEntityService<WebauthnCredentialDTO, Long> getService() {
        return service;
    }
}
