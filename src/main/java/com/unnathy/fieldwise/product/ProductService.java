package com.unnathy.fieldwise.product;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.product.ProductDTO;
import com.unnathy.fieldwise.product.Product;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.product.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ProductService implements BasicEntityService<ProductDTO, Long> {

    private final ProductRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public ProductDTO post(ProductDTO data, String authorization, User principal) throws UnnathyError {
        Product entity = modelMapperService.map(data, Product.class);
        Product saved = repository.save(entity);
        return modelMapperService.map(saved, ProductDTO.class);
    }

    @Override
    public ProductDTO patch(ProductDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public ProductDTO put(ProductDTO data, String authorization, User principal) throws UnnathyError {
        Product entity = modelMapperService.map(data, Product.class);
        Product saved = repository.save(entity);
        return modelMapperService.map(saved, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, ProductDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Product not found", null));
    }

    public List<ProductDTO> getWithCategoryAndSubCategory(Long categoryId,Long subCategoryId) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
