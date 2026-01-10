package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.OtpVerificationDTO;
import com.unnathy.fieldwise.entity.OtpVerification;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.repo.OtpVerificationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpVerificationService implements BasicEntityService<OtpVerificationDTO, Long> {

    private final OtpVerificationRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public OtpVerificationDTO post(OtpVerificationDTO data, String authorization, User principal) throws UnnathyError {
        OtpVerification entity = modelMapperService.map(data, OtpVerification.class);
        OtpVerification saved = repository.save(entity);
        return modelMapperService.map(saved, OtpVerificationDTO.class);
    }

    @Override
    public OtpVerificationDTO patch(OtpVerificationDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public OtpVerificationDTO put(OtpVerificationDTO data, String authorization, User principal) throws UnnathyError {
        OtpVerification entity = modelMapperService.map(data, OtpVerification.class);
        OtpVerification saved = repository.save(entity);
        return modelMapperService.map(saved, OtpVerificationDTO.class);
    }

    @Override
    public List<OtpVerificationDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, OtpVerificationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OtpVerificationDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, OtpVerificationDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "OtpVerification not found", null));
    }
}
