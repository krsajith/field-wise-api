package com.unnathy.fieldwise.productcategory;

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
public class ProductCategoryService implements BasicEntityService<ProductCategoryDTO, Long> {

    private final ProductCategoryRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public ProductCategoryDTO post(ProductCategoryDTO data, String authorization, User principal) throws UnnathyError {
        ProductCategory entity = modelMapperService.map(data, ProductCategory.class);
        ProductCategory saved = repository.save(entity);
        return modelMapperService.map(saved, ProductCategoryDTO.class);
    }

    @Override
    public ProductCategoryDTO patch(ProductCategoryDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public ProductCategoryDTO put(ProductCategoryDTO data, String authorization, User principal) throws UnnathyError {
        ProductCategory entity = modelMapperService.map(data, ProductCategory.class);
        ProductCategory saved = repository.save(entity);
        return modelMapperService.map(saved, ProductCategoryDTO.class);
    }

    @Override
    public List<ProductCategoryDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, ProductCategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategoryDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, ProductCategoryDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "ProductCategory not found", null));
    }
}
