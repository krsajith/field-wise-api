package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.AuditLogDTO;
import com.unnathy.fieldwise.entity.AuditLog;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.repo.AuditLogRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditLogService implements BasicEntityService<AuditLogDTO, Long> {

    private final AuditLogRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public AuditLogDTO post(AuditLogDTO data, String authorization, User principal) throws UnnathyError {
        AuditLog entity = modelMapperService.map(data, AuditLog.class);
        AuditLog saved = repository.save(entity);
        return modelMapperService.map(saved, AuditLogDTO.class);
    }

    @Override
    public AuditLogDTO patch(AuditLogDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public AuditLogDTO put(AuditLogDTO data, String authorization, User principal) throws UnnathyError {
        AuditLog entity = modelMapperService.map(data, AuditLog.class);
        AuditLog saved = repository.save(entity);
        return modelMapperService.map(saved, AuditLogDTO.class);
    }

    @Override
    public List<AuditLogDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, AuditLogDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AuditLogDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, AuditLogDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "AuditLog not found", null));
    }
}
