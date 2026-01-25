package com.unnathy.fieldwise.rolepermission;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class RolePermissionService implements BasicEntityService<RolePermissionDTO, RolePermissionDTO, Long> {

    private final RolePermissionRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public RolePermissionDTO post(RolePermissionDTO data, String authorization, User principal) throws UnnathyError {
        RolePermission entity = modelMapperService.map(data, RolePermission.class);
        RolePermission saved = repository.save(entity);
        return modelMapperService.map(saved, RolePermissionDTO.class);
    }

    @Override
    public RolePermissionDTO patch(RolePermissionDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public RolePermissionDTO put(RolePermissionDTO data, String authorization, User principal) throws UnnathyError {
        RolePermission entity = modelMapperService.map(data, RolePermission.class);
        RolePermission saved = repository.save(entity);
        return modelMapperService.map(saved, RolePermissionDTO.class);
    }

    @Override
    public List<RolePermissionDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, RolePermissionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RolePermissionDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, RolePermissionDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "RolePermission not found", null));
    }
}


