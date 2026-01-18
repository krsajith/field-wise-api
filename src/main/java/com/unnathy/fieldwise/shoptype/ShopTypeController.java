package com.unnathy.fieldwise.shoptype;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.shoptype.ShopTypeDTO;
import com.unnathy.fieldwise.shoptype.ShopTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/shopTypes")
@RequiredArgsConstructor
public class ShopTypeController implements BaseController<ShopTypeDTO, Long> {

    private final ShopTypeService service;

    @Override
    public BasicEntityService<ShopTypeDTO, Long> getService() {
        return service;
    }
}
