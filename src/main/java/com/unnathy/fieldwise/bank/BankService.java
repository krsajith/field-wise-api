package com.unnathy.fieldwise.bank;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.bank.BankDTO;
import com.unnathy.fieldwise.bank.Bank;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.bank.BankRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class BankService implements BasicEntityService<BankDTO, Long> {

    private final BankRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public BankDTO post(BankDTO data, String authorization, User principal) throws UnnathyError {
        Bank entity = modelMapperService.map(data, Bank.class);
        Bank saved = repository.save(entity);
        return modelMapperService.map(saved, BankDTO.class);
    }

    @Override
    public BankDTO patch(BankDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public BankDTO put(BankDTO data, String authorization, User principal) throws UnnathyError {
        Bank entity = modelMapperService.map(data, Bank.class);
        Bank saved = repository.save(entity);
        return modelMapperService.map(saved, BankDTO.class);
    }

    @Override
    public List<BankDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, BankDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BankDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, BankDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Bank not found", null));
    }
}
