package com.unnathy.fieldwise.shopvisit;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.shopvisitview.ShopVisitViewDTO;
import com.unnathy.fieldwise.shopvisitview.ShopVisitViewRepository;
import com.unnathy.fieldwise.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopVisitService implements BasicEntityService<ShopVisitDTO, Long> {

    private final ShopVisitRepository repository;
    private final ShopVisitViewRepository viewRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public ShopVisitDTO post(ShopVisitDTO data, String authorization, User principal) throws UnnathyError {
        ShopVisit entity = modelMapperService.map(data, ShopVisit.class);
        setDetails(principal, entity);
        ShopVisit saved = repository.save(entity);
        return modelMapperService.map(saved, ShopVisitDTO.class);
    }

    private static void setDetails(User principal, ShopVisit entity) {
        if(entity.getCheckInTime()==null){
            entity.setCheckInTime(Instant.now());
        }
        entity.setUserId(principal.getId());
    }

    @Override
    public ShopVisitDTO patch(ShopVisitDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public ShopVisitDTO put(ShopVisitDTO data, String authorization, User principal) throws UnnathyError {
        ShopVisit entity = modelMapperService.map(data, ShopVisit.class);
        setDetails(principal,entity);
        ShopVisit saved = repository.save(entity);
        return modelMapperService.map(saved, ShopVisitDTO.class);
    }

    @Override
    public List<ShopVisitDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, ShopVisitDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShopVisitDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, ShopVisitDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "ShopVisit not found", null));
    }

    public List<ShopVisitViewDTO> getShopVisitView() {
        return viewRepository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, ShopVisitViewDTO.class))
                .collect(Collectors.toList());
    }

    public ShopVisitDTO checkout(Long id) throws UnnathyError {
        ShopVisit entity = repository.findById(id)
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "ShopVisit not found", null));
        entity.setCheckOutTime(Instant.now());
        ShopVisit saved = repository.save(entity);
        return modelMapperService.map(saved, ShopVisitDTO.class);
    }
}
