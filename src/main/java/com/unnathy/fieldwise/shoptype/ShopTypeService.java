package com.unnathy.fieldwise.shoptype;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.user.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ShopTypeService implements BasicEntityService<ShopTypeDTO, Long> {

    private final ShopTypeRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public ShopTypeDTO post(ShopTypeDTO data, String authorization, User principal) throws UnnathyError {
        ShopType entity = modelMapperService.map(data, ShopType.class);
        ShopType saved = repository.save(entity);
        return modelMapperService.map(saved, ShopTypeDTO.class);
    }

    @Override
    public ShopTypeDTO patch(ShopTypeDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public ShopTypeDTO put(ShopTypeDTO data, String authorization, User principal) throws UnnathyError {
        ShopType entity = modelMapperService.map(data, ShopType.class);
        ShopType saved = repository.save(entity);
        return modelMapperService.map(saved, ShopTypeDTO.class);
    }

    @Override
    public List<ShopTypeDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, ShopTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShopTypeDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, ShopTypeDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "ShopType not found", null));
    }
}
