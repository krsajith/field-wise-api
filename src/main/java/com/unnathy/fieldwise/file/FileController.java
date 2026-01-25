package com.unnathy.fieldwise.file;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.file.FileDTO;
import com.unnathy.fieldwise.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController implements BaseController<FileDTO, FileDTO, Long> {

    private final FileService service;

    @Override
    public BasicEntityService<FileDTO, FileDTO, Long> getService() {
        return service;
    }
}



