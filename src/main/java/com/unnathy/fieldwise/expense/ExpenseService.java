package com.unnathy.fieldwise.expense;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.user.User;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ExpenseService implements BasicEntityService<ExpenseDTO, VwExpenseDTO, Long> {

    private final ExpenseRepository repository;
    private final ModelMapperService modelMapperService;
    private final VwExpenseRepository vwExpenseRepository;

    @Override
    public VwExpenseDTO post(ExpenseDTO data, String authorization, User principal) throws UnnathyError {
        Expense entity = modelMapperService.map(data, Expense.class);
        Expense saved = repository.save(entity);
        return getVwExpenseDto(saved.getId());
    }

    @Override
    public VwExpenseDTO patch(ExpenseDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public VwExpenseDTO put(ExpenseDTO data, String authorization, User principal) throws UnnathyError {
        Expense entity = modelMapperService.map(data, Expense.class);
        Expense saved = repository.save(entity);
        return getVwExpenseDto(saved.getId());
    }

    @Override
    public List<VwExpenseDTO> get(String authorization, User principal) throws UnnathyError {
        return vwExpenseRepository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, VwExpenseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public VwExpenseDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return vwExpenseRepository.findById(id)
                .map(entity -> modelMapperService.map(entity, VwExpenseDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Expense not found", null));
    }

    @Override
    public Page<VwExpenseDTO> getPaged(String authorization, User principal, int page, int size) throws UnnathyError {
        return vwExpenseRepository.findAll(PageRequest.of(page, size))
                .map(entity -> modelMapperService.map(entity, VwExpenseDTO.class));
    }

    private VwExpenseDTO getVwExpenseDto(Long id) throws UnnathyError {
        return vwExpenseRepository.findById(id)
                .map(entity -> modelMapperService.map(entity, VwExpenseDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Expense not found", null));
    }
}


