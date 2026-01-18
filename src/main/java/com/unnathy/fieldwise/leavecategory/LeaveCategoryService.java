package com.unnathy.fieldwise.leavecategory;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.leavecategory.LeaveCategoryDTO;
import com.unnathy.fieldwise.leavecategory.LeaveCategory;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.leavecategory.LeaveCategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class LeaveCategoryService implements BasicEntityService<LeaveCategoryDTO, Long> {

    private final LeaveCategoryRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public LeaveCategoryDTO post(LeaveCategoryDTO data, String authorization, User principal) throws UnnathyError {
        LeaveCategory entity = modelMapperService.map(data, LeaveCategory.class);
        LeaveCategory saved = repository.save(entity);
        return modelMapperService.map(saved, LeaveCategoryDTO.class);
    }

    @Override
    public LeaveCategoryDTO patch(LeaveCategoryDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public LeaveCategoryDTO put(LeaveCategoryDTO data, String authorization, User principal) throws UnnathyError {
        LeaveCategory entity = modelMapperService.map(data, LeaveCategory.class);
        LeaveCategory saved = repository.save(entity);
        return modelMapperService.map(saved, LeaveCategoryDTO.class);
    }

    @Override
    public List<LeaveCategoryDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, LeaveCategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LeaveCategoryDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, LeaveCategoryDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "LeaveCategory not found", null));
    }
}
