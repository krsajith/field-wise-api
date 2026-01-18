package com.unnathy.fieldwise.activity;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.activity.ActivityDTO;
import com.unnathy.fieldwise.activity.Activity;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.activity.ActivityRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ActivityService implements BasicEntityService<ActivityDTO, Long> {

    private final ActivityRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public ActivityDTO post(ActivityDTO data, String authorization, User principal) throws UnnathyError {
        Activity entity = modelMapperService.map(data, Activity.class);
        Activity saved = repository.save(entity);
        return modelMapperService.map(saved, ActivityDTO.class);
    }

    @Override
    public ActivityDTO patch(ActivityDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public ActivityDTO put(ActivityDTO data, String authorization, User principal) throws UnnathyError {
        Activity entity = modelMapperService.map(data, Activity.class);
        Activity saved = repository.save(entity);
        return modelMapperService.map(saved, ActivityDTO.class);
    }

    @Override
    public List<ActivityDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, ActivityDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ActivityDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, ActivityDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Activity not found", null));
    }
}
