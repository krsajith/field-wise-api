package com.unnathy.fieldwise.core;

import com.unnathy.fieldwise.dto.BaseDTO;
import com.unnathy.fieldwise.user.User;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

public interface BasicEntityService<T extends BaseDTO, I extends Serializable> {

    T post(T data, String authorization, User principal) throws UnnathyError;

    T patch(T data, String authorization, User principal) throws UnnathyError;

    T put(@RequestBody T data, String authorization, User principal) throws UnnathyError;

    List<T> get(String authorization, User principal)  throws UnnathyError;

    T getWithId(String authorization, User principal, I id) throws UnnathyError;

    default Page<? extends T> getPaged(String authorization, User principal, int page, int size) throws UnnathyError {
        throw new UnnathyError("Paged endpoint not implemented",null,null);
    }
}
