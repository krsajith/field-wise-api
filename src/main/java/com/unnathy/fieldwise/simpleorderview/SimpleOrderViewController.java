package com.unnathy.fieldwise.simpleorderview;

import com.unnathy.fieldwise.simpleorderview.SimpleOrderViewDTO;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.simpleorderview.SimpleOrderViewService;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/simpleOrderViews")
@RequiredArgsConstructor
public class SimpleOrderViewController {

    private final SimpleOrderViewService service;

    @GetMapping
    public List<SimpleOrderViewDTO> getAll(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal) {
        return service.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<SimpleOrderViewDTO> getByUserId(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal,
            @PathVariable("userId") Long userId) {
        return service.getByUserId(userId);
    }
}
