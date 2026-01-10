package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.AttendanceDTO;
import com.unnathy.fieldwise.entity.Attendance;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.repo.AttendanceRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService implements BasicEntityService<AttendanceDTO, Long> {

    private final AttendanceRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public AttendanceDTO post(AttendanceDTO data, String authorization, User principal) throws UnnathyError {
        Attendance entity = modelMapperService.map(data, Attendance.class);
        Attendance saved = repository.save(entity);
        return modelMapperService.map(saved, AttendanceDTO.class);
    }

    @Override
    public AttendanceDTO patch(AttendanceDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public AttendanceDTO put(AttendanceDTO data, String authorization, User principal) throws UnnathyError {
        Attendance entity = modelMapperService.map(data, Attendance.class);
        Attendance saved = repository.save(entity);
        return modelMapperService.map(saved, AttendanceDTO.class);
    }

    @Override
    public List<AttendanceDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, AttendanceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, AttendanceDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Attendance not found", null));
    }
}
