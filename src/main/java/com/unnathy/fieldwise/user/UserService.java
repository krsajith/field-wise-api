package com.unnathy.fieldwise.user;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.user.UserDTO;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.user.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService implements BasicEntityService<UserDTO, UserDTO, Long> {

    private final UserRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public UserDTO post(UserDTO data, String authorization, User principal) throws UnnathyError {
        User entity = modelMapperService.map(data, User.class);
        User saved = repository.save(entity);
        return modelMapperService.map(saved, UserDTO.class);
    }

    @Override
    public UserDTO patch(UserDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public UserDTO put(UserDTO data, String authorization, User principal) throws UnnathyError {
        User entity = modelMapperService.map(data, User.class);
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
}


