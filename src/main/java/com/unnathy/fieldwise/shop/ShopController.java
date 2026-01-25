package com.unnathy.fieldwise.shop;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.shop.ShopDTO;
import com.unnathy.fieldwise.shop.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/shops")
@RequiredArgsConstructor
public class ShopController implements BaseController<ShopDTO, ShopDTO, Long> {

    private final ShopService service;

    @Override
    public BasicEntityService<ShopDTO, ShopDTO, Long> getService() {
        return service;
    }
}



