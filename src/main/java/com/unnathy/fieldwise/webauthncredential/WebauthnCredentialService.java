package com.unnathy.fieldwise.webauthncredential;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.webauthncredential.WebauthnCredentialDTO;
import com.unnathy.fieldwise.webauthncredential.WebauthnCredential;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.webauthncredential.WebauthnCredentialRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class WebauthnCredentialService implements BasicEntityService<WebauthnCredentialDTO, WebauthnCredentialDTO, Long> {

    private final WebauthnCredentialRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public WebauthnCredentialDTO post(WebauthnCredentialDTO data, String authorization, User principal) throws UnnathyError {
        WebauthnCredential entity = modelMapperService.map(data, WebauthnCredential.class);
        WebauthnCredential saved = repository.save(entity);
        return modelMapperService.map(saved, WebauthnCredentialDTO.class);
    }

    @Override
    public WebauthnCredentialDTO patch(WebauthnCredentialDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public WebauthnCredentialDTO put(WebauthnCredentialDTO data, String authorization, User principal) throws UnnathyError {
        WebauthnCredential entity = modelMapperService.map(data, WebauthnCredential.class);
        WebauthnCredential saved = repository.save(entity);
        return modelMapperService.map(saved, WebauthnCredentialDTO.class);
    }

    @Override
    public List<WebauthnCredentialDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, WebauthnCredentialDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public WebauthnCredentialDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, WebauthnCredentialDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "WebauthnCredential not found", null));
    }
}


