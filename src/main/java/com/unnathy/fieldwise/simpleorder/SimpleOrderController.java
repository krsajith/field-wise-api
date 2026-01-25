package com.unnathy.fieldwise.simpleorder;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.simpleorder.SimpleOrderDTO;
import com.unnathy.fieldwise.simpleorder.SimpleOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/simpleOrders")
@RequiredArgsConstructor
public class SimpleOrderController implements BaseController<SimpleOrderDTO, SimpleOrderDTO, Long> {

    private final SimpleOrderService service;

    @Override
    public BasicEntityService<SimpleOrderDTO, SimpleOrderDTO, Long> getService() {
        return service;
    }
}



