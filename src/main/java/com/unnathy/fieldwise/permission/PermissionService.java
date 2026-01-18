package com.unnathy.fieldwise.permission;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.permission.PermissionDTO;
import com.unnathy.fieldwise.permission.Permission;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.permission.PermissionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class PermissionService implements BasicEntityService<PermissionDTO, Long> {

    private final PermissionRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public PermissionDTO post(PermissionDTO data, String authorization, User principal) throws UnnathyError {
        Permission entity = modelMapperService.map(data, Permission.class);
        Permission saved = repository.save(entity);
        return modelMapperService.map(saved, PermissionDTO.class);
    }

    @Override
    public PermissionDTO patch(PermissionDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public PermissionDTO put(PermissionDTO data, String authorization, User principal) throws UnnathyError {
        Permission entity = modelMapperService.map(data, Permission.class);
        Permission saved = repository.save(entity);
        return modelMapperService.map(saved, PermissionDTO.class);
    }

    @Override
    public List<PermissionDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, PermissionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PermissionDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, PermissionDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Permission not found", null));
    }
}
