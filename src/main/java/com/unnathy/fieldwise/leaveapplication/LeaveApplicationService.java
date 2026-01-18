package com.unnathy.fieldwise.leaveapplication;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.leaveapplication.LeaveApplicationDTO;
import com.unnathy.fieldwise.leaveapplication.LeaveApplication;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.leaveapplication.LeaveApplicationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class LeaveApplicationService implements BasicEntityService<LeaveApplicationDTO, Long> {

    private final LeaveApplicationRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public LeaveApplicationDTO post(LeaveApplicationDTO data, String authorization, User principal) throws UnnathyError {
        LeaveApplication entity = modelMapperService.map(data, LeaveApplication.class);
        LeaveApplication saved = repository.save(entity);
        return modelMapperService.map(saved, LeaveApplicationDTO.class);
    }

    @Override
    public LeaveApplicationDTO patch(LeaveApplicationDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public LeaveApplicationDTO put(LeaveApplicationDTO data, String authorization, User principal) throws UnnathyError {
        LeaveApplication entity = modelMapperService.map(data, LeaveApplication.class);
        LeaveApplication saved = repository.save(entity);
        return modelMapperService.map(saved, LeaveApplicationDTO.class);
    }

    @Override
    public List<LeaveApplicationDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, LeaveApplicationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LeaveApplicationDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, LeaveApplicationDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "LeaveApplication not found", null));
    }
}
