package com.unnathy.fieldwise.role;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.role.RoleDTO;
import com.unnathy.fieldwise.role.Role;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.role.RoleRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class RoleService implements BasicEntityService<RoleDTO, Long> {

    private final RoleRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public RoleDTO post(RoleDTO data, String authorization, User principal) throws UnnathyError {
        Role entity = modelMapperService.map(data, Role.class);
        Role saved = repository.save(entity);
        return modelMapperService.map(saved, RoleDTO.class);
    }

    @Override
    public RoleDTO patch(RoleDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public RoleDTO put(RoleDTO data, String authorization, User principal) throws UnnathyError {
        Role entity = modelMapperService.map(data, Role.class);
        Role saved = repository.save(entity);
        return modelMapperService.map(saved, RoleDTO.class);
    }

    @Override
    public List<RoleDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, RoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, RoleDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Role not found", null));
    }
}
