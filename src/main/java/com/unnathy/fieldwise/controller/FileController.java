package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.FileDTO;
import com.unnathy.fieldwise.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController implements BaseController<FileDTO, Long> {

    private final FileService service;

    @Override
    public BasicEntityService<FileDTO, Long> getService() {
        return service;
    }
}
