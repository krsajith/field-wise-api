package com.unnathy.fieldwise.core;

import com.unnathy.fieldwise.dto.BaseDTO;
import com.unnathy.fieldwise.user.User;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;


public interface BaseController<WriteDTO extends BaseDTO, ReadDTO extends BaseDTO, I extends Serializable> extends SecuredRestController {

    @PostMapping()
    default ReadDTO post(@RequestBody WriteDTO data, @Parameter(hidden = true) @RequestHeader("Authorization") String authorization, @Parameter(hidden = true) @AuthenticationPrincipal User principal) throws UnnathyError {
        return getService().post(data, authorization, principal);
    }

    @PatchMapping()
    default ReadDTO patch(@RequestBody WriteDTO data, @Parameter(hidden = true) @RequestHeader("Authorization") String authorization, @Parameter(hidden = true) @AuthenticationPrincipal User principal) throws UnnathyError {
        return getService().patch(data,authorization,principal);
    }

    @PutMapping()
    default ReadDTO put(@RequestBody WriteDTO data, @Parameter(hidden = true) @RequestHeader("Authorization") String authorization, @Parameter(hidden = true) @AuthenticationPrincipal User principal) throws UnnathyError {
        return getService().put(data,authorization,principal);
    }

    @GetMapping()
    default List<ReadDTO> get(@Parameter(hidden = true) @RequestHeader("Authorization") String authorization, @Parameter(hidden = true) @AuthenticationPrincipal User principal) throws UnnathyError {
        return getService().get(authorization, principal);
    }

    @GetMapping("/page")
    default Page<ReadDTO> getPaged(@Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
                                   @Parameter(hidden = true) @AuthenticationPrincipal User principal,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "20") int size) throws UnnathyError {
        return getService().getPaged(authorization, principal, page, size);
    }

    @GetMapping("/{id}")
    default ReadDTO getWithId(@Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
                        @Parameter(hidden = true) @AuthenticationPrincipal User principal,
                        @PathVariable("id") I id) throws UnnathyError {
        return getService().getWithId(authorization, principal, id);
    }

    BasicEntityService<WriteDTO, ReadDTO, I> getService();
}

