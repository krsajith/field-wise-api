package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.PushNotificationDTO;
import com.unnathy.fieldwise.entity.PushNotification;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.repo.PushNotificationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PushNotificationService implements BasicEntityService<PushNotificationDTO, Long> {

    private final PushNotificationRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public PushNotificationDTO post(PushNotificationDTO data, String authorization, User principal) throws UnnathyError {
        PushNotification entity = modelMapperService.map(data, PushNotification.class);
        PushNotification saved = repository.save(entity);
        return modelMapperService.map(saved, PushNotificationDTO.class);
    }

    @Override
    public PushNotificationDTO patch(PushNotificationDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public PushNotificationDTO put(PushNotificationDTO data, String authorization, User principal) throws UnnathyError {
        PushNotification entity = modelMapperService.map(data, PushNotification.class);
        PushNotification saved = repository.save(entity);
        return modelMapperService.map(saved, PushNotificationDTO.class);
    }

    @Override
    public List<PushNotificationDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, PushNotificationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PushNotificationDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, PushNotificationDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "PushNotification not found", null));
    }
}
