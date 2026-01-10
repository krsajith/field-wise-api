package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.ProductStockDTO;
import com.unnathy.fieldwise.entity.ProductStock;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.repo.ProductStockRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductStockService implements BasicEntityService<ProductStockDTO, Long> {

    private final ProductStockRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public ProductStockDTO post(ProductStockDTO data, String authorization, User principal) throws UnnathyError {
        ProductStock entity = modelMapperService.map(data, ProductStock.class);
        ProductStock saved = repository.save(entity);
        return modelMapperService.map(saved, ProductStockDTO.class);
    }

    @Override
    public ProductStockDTO patch(ProductStockDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public ProductStockDTO put(ProductStockDTO data, String authorization, User principal) throws UnnathyError {
        ProductStock entity = modelMapperService.map(data, ProductStock.class);
        ProductStock saved = repository.save(entity);
        return modelMapperService.map(saved, ProductStockDTO.class);
    }

    @Override
    public List<ProductStockDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, ProductStockDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductStockDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, ProductStockDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "ProductStock not found", null));
    }
}
