package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.PaymentTermDTO;
import com.unnathy.fieldwise.entity.PaymentTerm;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.repo.PaymentTermRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentTermService implements BasicEntityService<PaymentTermDTO, Long> {

    private final PaymentTermRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public PaymentTermDTO post(PaymentTermDTO data, String authorization, User principal) throws UnnathyError {
        PaymentTerm entity = modelMapperService.map(data, PaymentTerm.class);
        PaymentTerm saved = repository.save(entity);
        return modelMapperService.map(saved, PaymentTermDTO.class);
    }

    @Override
    public PaymentTermDTO patch(PaymentTermDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public PaymentTermDTO put(PaymentTermDTO data, String authorization, User principal) throws UnnathyError {
        PaymentTerm entity = modelMapperService.map(data, PaymentTerm.class);
        PaymentTerm saved = repository.save(entity);
        return modelMapperService.map(saved, PaymentTermDTO.class);
    }

    @Override
    public List<PaymentTermDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, PaymentTermDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PaymentTermDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, PaymentTermDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "PaymentTerm not found", null));
    }
}
