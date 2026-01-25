package com.unnathy.fieldwise.productsubcategory;

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
public class ProductSubCategoryService implements BasicEntityService<ProductSubCategoryDTO, ProductSubCategoryDTO, Long> {

    private final ProductSubCategoryRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public ProductSubCategoryDTO post(ProductSubCategoryDTO data, String authorization, User principal) throws UnnathyError {
        ProductSubCategory entity = modelMapperService.map(data, ProductSubCategory.class);
        ProductSubCategory saved = repository.save(entity);
        return modelMapperService.map(saved, ProductSubCategoryDTO.class);
    }

    @Override
    public ProductSubCategoryDTO patch(ProductSubCategoryDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public ProductSubCategoryDTO put(ProductSubCategoryDTO data, String authorization, User principal) throws UnnathyError {
        ProductSubCategory entity = modelMapperService.map(data, ProductSubCategory.class);
        ProductSubCategory saved = repository.save(entity);
        return modelMapperService.map(saved, ProductSubCategoryDTO.class);
    }

    @Override
    public List<ProductSubCategoryDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, ProductSubCategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductSubCategoryDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, ProductSubCategoryDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "ProductSubCategory not found", null));
    }

    public List<ProductSubCategoryDTO> getByCategory(String authorization, User principal, Long categoryId)
            throws UnnathyError {
        return repository.findByCategoryId(categoryId).stream()
                .map(entity -> modelMapperService.map(entity, ProductSubCategoryDTO.class))
                .collect(Collectors.toList());
    }
}


