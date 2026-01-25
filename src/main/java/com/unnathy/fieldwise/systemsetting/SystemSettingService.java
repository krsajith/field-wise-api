package com.unnathy.fieldwise.systemsetting;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.systemsetting.SystemSettingDTO;
import com.unnathy.fieldwise.systemsetting.SystemSetting;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.systemsetting.SystemSettingRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class SystemSettingService implements BasicEntityService<SystemSettingDTO, SystemSettingDTO, Long> {

    private final SystemSettingRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public SystemSettingDTO post(SystemSettingDTO data, String authorization, User principal) throws UnnathyError {
        SystemSetting entity = modelMapperService.map(data, SystemSetting.class);
        SystemSetting saved = repository.save(entity);
        return modelMapperService.map(saved, SystemSettingDTO.class);
    }

    @Override
    public SystemSettingDTO patch(SystemSettingDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public SystemSettingDTO put(SystemSettingDTO data, String authorization, User principal) throws UnnathyError {
        SystemSetting entity = modelMapperService.map(data, SystemSetting.class);
        SystemSetting saved = repository.save(entity);
        return modelMapperService.map(saved, SystemSettingDTO.class);
    }

    @Override
    public List<SystemSettingDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, SystemSettingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SystemSettingDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, SystemSettingDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "SystemSetting not found", null));
    }
}


