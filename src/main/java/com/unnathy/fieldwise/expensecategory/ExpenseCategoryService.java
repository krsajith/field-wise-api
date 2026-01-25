package com.unnathy.fieldwise.expensecategory;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
  import com.unnathy.fieldwise.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class ExpenseCategoryService implements BasicEntityService<ExpenseCategoryDTO, ExpenseCategoryDTO, Long> {

    private final ExpenseCategoryRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public ExpenseCategoryDTO post(ExpenseCategoryDTO data, String authorization, User principal) throws UnnathyError {
        ExpenseCategory entity = modelMapperService.map(data, ExpenseCategory.class);
        ExpenseCategory saved = repository.save(entity);
        return modelMapperService.map(saved, ExpenseCategoryDTO.class);
    }

    @Override
    public ExpenseCategoryDTO patch(ExpenseCategoryDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public ExpenseCategoryDTO put(ExpenseCategoryDTO data, String authorization, User principal) throws UnnathyError {
        ExpenseCategory entity = modelMapperService.map(data, ExpenseCategory.class);
        ExpenseCategory saved = repository.save(entity);
        return modelMapperService.map(saved, ExpenseCategoryDTO.class);
    }

    @Override
    public List<ExpenseCategoryDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, ExpenseCategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseCategoryDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, ExpenseCategoryDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "ExpenseCategory not found", null));
    }
}


