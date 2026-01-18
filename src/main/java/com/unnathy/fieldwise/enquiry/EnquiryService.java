package com.unnathy.fieldwise.enquiry;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.enquiry.EnquiryDTO;
import com.unnathy.fieldwise.enquiry.Enquiry;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.enquiry.EnquiryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class EnquiryService implements BasicEntityService<EnquiryDTO, Long> {

    private final EnquiryRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public EnquiryDTO post(EnquiryDTO data, String authorization, User principal) throws UnnathyError {
        Enquiry entity = modelMapperService.map(data, Enquiry.class);
        Enquiry saved = repository.save(entity);
        return modelMapperService.map(saved, EnquiryDTO.class);
    }

    @Override
    public EnquiryDTO patch(EnquiryDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public EnquiryDTO put(EnquiryDTO data, String authorization, User principal) throws UnnathyError {
        Enquiry entity = modelMapperService.map(data, Enquiry.class);
        Enquiry saved = repository.save(entity);
        return modelMapperService.map(saved, EnquiryDTO.class);
    }

    @Override
    public List<EnquiryDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, EnquiryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EnquiryDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, EnquiryDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Enquiry not found", null));
    }
}
