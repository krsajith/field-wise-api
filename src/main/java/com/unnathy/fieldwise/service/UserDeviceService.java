package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.UserDeviceDTO;
import com.unnathy.fieldwise.entity.UserDevice;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.repo.UserDeviceRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeviceService implements BasicEntityService<UserDeviceDTO, Long> {

    private final UserDeviceRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public UserDeviceDTO post(UserDeviceDTO data, String authorization, User principal) throws UnnathyError {
        UserDevice entity = modelMapperService.map(data, UserDevice.class);
        UserDevice saved = repository.save(entity);
        return modelMapperService.map(saved, UserDeviceDTO.class);
    }

    @Override
    public UserDeviceDTO patch(UserDeviceDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public UserDeviceDTO put(UserDeviceDTO data, String authorization, User principal) throws UnnathyError {
        UserDevice entity = modelMapperService.map(data, UserDevice.class);
        UserDevice saved = repository.save(entity);
        return modelMapperService.map(saved, UserDeviceDTO.class);
    }

    @Override
    public List<UserDeviceDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, UserDeviceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDeviceDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, UserDeviceDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "UserDevice not found", null));
    }
}
