package com.unnathy.fieldwise.product;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.product.ProductDTO;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.product.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.List;



import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController implements BaseController<ProductDTO, Long> {

    private final ProductService service;

    @Override
    public BasicEntityService<ProductDTO, Long> getService() {
        return service;
    }


    @GetMapping("/by-category/{categoryId}/{subCategoryId}")
    public List<ProductDTO> getByCategory(
            @PathVariable Long categoryId,
            @PathVariable Long subCategoryId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal) throws UnnathyError {
        return service.getWithCategoryAndSubCategory(categoryId,subCategoryId);
    }
}
