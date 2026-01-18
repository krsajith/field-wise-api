package com.unnathy.fieldwise.productsubcategory;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.productsubcategory.ProductSubCategoryDTO;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.productsubcategory.ProductSubCategoryService;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productSubCategorys")
@RequiredArgsConstructor
public class ProductSubCategoryController implements BaseController<ProductSubCategoryDTO, Long> {

    private final ProductSubCategoryService service;

    @Override
    public BasicEntityService<ProductSubCategoryDTO, Long> getService() {
        return service;
    }

    @GetMapping("/by-category/{categoryId}")
    public List<ProductSubCategoryDTO> getByCategory(
            @PathVariable("categoryId") Long categoryId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal) throws UnnathyError {
        return service.getByCategory(authorization, principal, categoryId);
    }
}
