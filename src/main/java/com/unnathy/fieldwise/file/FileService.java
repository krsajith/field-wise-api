package com.unnathy.fieldwise.file;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.file.FileDTO;
import com.unnathy.fieldwise.file.File;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.file.FileRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class FileService implements BasicEntityService<FileDTO, FileDTO, Long> {

    private final FileRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public FileDTO post(FileDTO data, String authorization, User principal) throws UnnathyError {
        File entity = modelMapperService.map(data, File.class);
        File saved = repository.save(entity);
        return modelMapperService.map(saved, FileDTO.class);
    }

    @Override
    public FileDTO patch(FileDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public FileDTO put(FileDTO data, String authorization, User principal) throws UnnathyError {
        File entity = modelMapperService.map(data, File.class);
        File saved = repository.save(entity);
        return modelMapperService.map(saved, FileDTO.class);
    }

    @Override
    public List<FileDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, FileDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FileDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, FileDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "File not found", null));
    }
}


