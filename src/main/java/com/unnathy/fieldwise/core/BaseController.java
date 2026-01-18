package com.unnathy.fieldwise.core;

import com.unnathy.fieldwise.dto.BaseDTO;
import com.unnathy.fieldwise.user.User;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.util.List;
import java.io.Serializable;
import java.util.List;



import java.io.Serializable;
import java.util.List;


public interface BaseController<T extends BaseDTO,I extends Serializable> extends SecuredRestController {

    @PostMapping()
    default T post(@RequestBody T data, @Parameter(hidden = true) @RequestHeader("Authorization") String authorization, @Parameter(hidden = true) @AuthenticationPrincipal User principal) throws UnnathyError {
        return getService().post(data, authorization, principal);
    }

    @PatchMapping()
    public default T patch(@RequestBody T data, @Parameter(hidden = true) @RequestHeader("Authorization") String authorization, @Parameter(hidden = true) @AuthenticationPrincipal User principal) throws UnnathyError {
        return getService().patch(data,authorization,principal);
    }

    @PutMapping()
    default T put(@RequestBody T data, @Parameter(hidden = true) @RequestHeader("Authorization") String authorization, @Parameter(hidden = true) @AuthenticationPrincipal User principal) throws UnnathyError {
        return getService().put(data,authorization,principal);
    }

    @GetMapping()
    default List<T> get(@Parameter(hidden = true) @RequestHeader("Authorization") String authorization, @Parameter(hidden = true) @AuthenticationPrincipal User principal) throws UnnathyError {
        return getService().get(authorization, principal);
    }

    @GetMapping("/{id}")
    default T getWithId(@Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
                        @Parameter(hidden = true) @AuthenticationPrincipal User principal,
                        @PathVariable("id") I id) throws UnnathyError {
        return getService().getWithId(authorization, principal, id);
    }

    BasicEntityService<T,I> getService();
}
