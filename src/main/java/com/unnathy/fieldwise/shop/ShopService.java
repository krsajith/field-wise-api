package com.unnathy.fieldwise.shop;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.shop.ShopDTO;
import com.unnathy.fieldwise.shop.Shop;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.shop.ShopRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ShopService implements BasicEntityService<ShopDTO, Long> {

    private final ShopRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public ShopDTO post(ShopDTO data, String authorization, User principal) throws UnnathyError {
        Shop entity = modelMapperService.map(data, Shop.class);
        Shop saved = repository.save(entity);
        return modelMapperService.map(saved, ShopDTO.class);
    }

    @Override
    public ShopDTO patch(ShopDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public ShopDTO put(ShopDTO data, String authorization, User principal) throws UnnathyError {
        Shop entity = modelMapperService.map(data, Shop.class);
        Shop saved = repository.save(entity);
        return modelMapperService.map(saved, ShopDTO.class);
    }

    @Override
    public List<ShopDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, ShopDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShopDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, ShopDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Shop not found", null));
    }
}
