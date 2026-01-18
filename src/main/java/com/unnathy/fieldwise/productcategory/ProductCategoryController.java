package com.unnathy.fieldwise.productcategory;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.productcategory.ProductCategoryDTO;
import com.unnathy.fieldwise.productcategory.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/productCategorys")
@RequiredArgsConstructor
public class ProductCategoryController implements BaseController<ProductCategoryDTO, Long> {

    private final ProductCategoryService service;

    @Override
    public BasicEntityService<ProductCategoryDTO, Long> getService() {
        return service;
    }
}
