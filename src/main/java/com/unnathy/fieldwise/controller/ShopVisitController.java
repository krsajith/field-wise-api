package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.ShopVisitDTO;
import com.unnathy.fieldwise.dto.ShopVisitViewDTO;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.service.ShopVisitService;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shopVisits")
@RequiredArgsConstructor
public class ShopVisitController implements BaseController<ShopVisitDTO, Long> {

    private final ShopVisitService service;

    @Override
    public BasicEntityService<ShopVisitDTO, Long> getService() {
        return service;
    }

    @GetMapping("/view")
    public List<ShopVisitViewDTO> getShopVisitView(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal) {
        return service.getShopVisitView();
    }

    @PatchMapping("/{id}/checkout")
    public ShopVisitDTO checkout(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal,
            @PathVariable("id") Long id) throws UnnathyError {
        return service.checkout(id);
    }
}
