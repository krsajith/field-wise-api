package com.unnathy.fieldwise.feedback;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.feedback.FeedbackDTO;
import com.unnathy.fieldwise.feedback.Feedback;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.feedback.FeedbackRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class FeedbackService implements BasicEntityService<FeedbackDTO, FeedbackDTO, Long> {

    private final FeedbackRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public FeedbackDTO post(FeedbackDTO data, String authorization, User principal) throws UnnathyError {
        Feedback entity = modelMapperService.map(data, Feedback.class);
        Feedback saved = repository.save(entity);
        return modelMapperService.map(saved, FeedbackDTO.class);
    }

    @Override
    public FeedbackDTO patch(FeedbackDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public FeedbackDTO put(FeedbackDTO data, String authorization, User principal) throws UnnathyError {
        Feedback entity = modelMapperService.map(data, Feedback.class);
        Feedback saved = repository.save(entity);
        return modelMapperService.map(saved, FeedbackDTO.class);
    }

    @Override
    public List<FeedbackDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, FeedbackDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, FeedbackDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Feedback not found", null));
    }
}


