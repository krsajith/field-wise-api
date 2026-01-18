package com.unnathy.fieldwise.target;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.target.TargetDTO;
import com.unnathy.fieldwise.target.Target;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.target.TargetRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class TargetService implements BasicEntityService<TargetDTO, Long> {

    private final TargetRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public TargetDTO post(TargetDTO data, String authorization, User principal) throws UnnathyError {
        Target entity = modelMapperService.map(data, Target.class);
        Target saved = repository.save(entity);
        return modelMapperService.map(saved, TargetDTO.class);
    }

    @Override
    public TargetDTO patch(TargetDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public TargetDTO put(TargetDTO data, String authorization, User principal) throws UnnathyError {
        Target entity = modelMapperService.map(data, Target.class);
        Target saved = repository.save(entity);
        return modelMapperService.map(saved, TargetDTO.class);
    }

    @Override
    public List<TargetDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, TargetDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TargetDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, TargetDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Target not found", null));
    }
}
