package com.unnathy.fieldwise.state;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.state.StateDTO;
import com.unnathy.fieldwise.state.State;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.state.StateRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class StateService implements BasicEntityService<StateDTO, Long> {

    private final StateRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public StateDTO post(StateDTO data, String authorization, User principal) throws UnnathyError {
        State entity = modelMapperService.map(data, State.class);
        State saved = repository.save(entity);
        return modelMapperService.map(saved, StateDTO.class);
    }

    @Override
    public StateDTO patch(StateDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public StateDTO put(StateDTO data, String authorization, User principal) throws UnnathyError {
        State entity = modelMapperService.map(data, State.class);
        State saved = repository.save(entity);
        return modelMapperService.map(saved, StateDTO.class);
    }

    @Override
    public List<StateDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, StateDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StateDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, StateDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "State not found", null));
    }
}
