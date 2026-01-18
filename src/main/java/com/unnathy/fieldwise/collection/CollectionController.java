package com.unnathy.fieldwise.collection;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.collection.CollectionDTO;
import com.unnathy.fieldwise.collection.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class CollectionController implements BaseController<CollectionDTO, Long> {

    private final CollectionService service;

    @Override
    public BasicEntityService<CollectionDTO, Long> getService() {
        return service;
    }
}
