package com.unnathy.fieldwise.userrouteassignment;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.userrouteassignment.UserRouteAssignmentDTO;

import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.userrouteassignment.UserRouteAssignmentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserRouteAssignmentService implements BasicEntityService<UserRouteAssignmentDTO, UserRouteAssignmentDTO, Long> {

    private final UserRouteAssignmentRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public UserRouteAssignmentDTO post(UserRouteAssignmentDTO data, String authorization, User principal) throws UnnathyError {
        UserRouteAssignment entity = modelMapperService.map(data, UserRouteAssignment.class);
        UserRouteAssignment saved = repository.save(entity);
        return modelMapperService.map(saved, UserRouteAssignmentDTO.class);
    }

    @Override
    public UserRouteAssignmentDTO patch(UserRouteAssignmentDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public UserRouteAssignmentDTO put(UserRouteAssignmentDTO data, String authorization, User principal) throws UnnathyError {
        UserRouteAssignment entity = modelMapperService.map(data, UserRouteAssignment.class);
        UserRouteAssignment saved = repository.save(entity);
        return modelMapperService.map(saved, UserRouteAssignmentDTO.class);
    }

    @Override
    public List<UserRouteAssignmentDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, UserRouteAssignmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserRouteAssignmentDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, UserRouteAssignmentDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "UserRouteAssignment not found", null));
    }
}


