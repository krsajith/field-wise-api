package com.unnathy.fieldwise.user;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class UserService implements BasicEntityService<UserDTO, UserDTO, Long> {

    private final UserRepository repository;
    private final ModelMapperService modelMapperService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDTO post(UserDTO data, String authorization, User principal) throws UnnathyError {
        return getUserDTO(data, principal);
    }

    @Override
    public UserDTO patch(UserDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public UserDTO put(UserDTO data, String authorization, User principal) throws UnnathyError {
        return getUserDTO(data, principal);
    }

    private UserDTO getUserDTO(UserDTO data, User principal) {
        User entity = modelMapperService.map(data, User.class);
        applyPasswordHash(entity, data);
        entity.setCreatedBy(principal.getId());
        entity.setCreatedAt(Instant.now());
        entity.setUpdatedAt(Instant.now());
        entity.setUpdatedBy(principal.getId());
        User saved = repository.save(entity);
        return modelMapperService.map(saved, UserDTO.class);
    }

    @Override
    public List<UserDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, UserDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "User not found", null));
    }

    private void applyPasswordHash(User entity, UserDTO data) {
        String passwordHash = data == null ? null : data.getPasswordHash();
        if (passwordHash == null || passwordHash.isBlank()) {
            entity.setPasswordHash(null);
            return;
        }
        if (isBcryptHash(passwordHash)) {
            entity.setPasswordHash(passwordHash);
            return;
        }
        entity.setPasswordHash(passwordEncoder.encode(passwordHash));
    }

    private boolean isBcryptHash(String value) {
        return value.startsWith("$2a$") || value.startsWith("$2b$") || value.startsWith("$2y$");
    }
}


