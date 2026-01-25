package com.unnathy.fieldwise.complaint;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.complaint.ComplaintDTO;
import com.unnathy.fieldwise.complaint.Complaint;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.complaint.ComplaintRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ComplaintService implements BasicEntityService<ComplaintDTO, ComplaintDTO, Long> {

    private final ComplaintRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public ComplaintDTO post(ComplaintDTO data, String authorization, User principal) throws UnnathyError {
        Complaint entity = modelMapperService.map(data, Complaint.class);
        Complaint saved = repository.save(entity);
        return modelMapperService.map(saved, ComplaintDTO.class);
    }

    @Override
    public ComplaintDTO patch(ComplaintDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public ComplaintDTO put(ComplaintDTO data, String authorization, User principal) throws UnnathyError {
        Complaint entity = modelMapperService.map(data, Complaint.class);
        Complaint saved = repository.save(entity);
        return modelMapperService.map(saved, ComplaintDTO.class);
    }

    @Override
    public List<ComplaintDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, ComplaintDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ComplaintDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, ComplaintDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Complaint not found", null));
    }
}


