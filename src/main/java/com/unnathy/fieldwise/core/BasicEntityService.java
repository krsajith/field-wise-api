package com.taomish.web.service;

import com.taomish.common.domain.TaomishError;
import com.taomish.common.jpa.BaseDto;
import com.taomish.web.security.models.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

public interface BasicEntityService<T extends BaseDto, I extends Serializable> {

    T post(T data, String authorization, User principal) throws TaomishError;

    T patch(T data, String authorization, User principal) throws TaomishError;

    T put(@RequestBody T data, String authorization, User principal) throws TaomishError;

    List<T> get(String authorization, User principal)  throws TaomishError;

    T getWithId(String authorization, User principal, I id) throws TaomishError;
}
