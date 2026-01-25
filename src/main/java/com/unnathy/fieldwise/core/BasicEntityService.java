package com.unnathy.fieldwise.core;

import com.unnathy.fieldwise.dto.BaseDTO;
import com.unnathy.fieldwise.user.User;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

public interface BasicEntityService<WriteDTO extends BaseDTO, ReadDTO extends BaseDTO, I extends Serializable> {

    ReadDTO post(WriteDTO data, String authorization, User principal) throws UnnathyError;

    ReadDTO patch(WriteDTO data, String authorization, User principal) throws UnnathyError;

    ReadDTO put(@RequestBody WriteDTO data, String authorization, User principal) throws UnnathyError;

    List<ReadDTO> get(String authorization, User principal)  throws UnnathyError;

    ReadDTO getWithId(String authorization, User principal, I id) throws UnnathyError;

    default Page<ReadDTO> getPaged(String authorization, User principal, int page, int size) throws UnnathyError {
        throw new UnnathyError("Paged endpoint not implemented",null,null);
    }
}
