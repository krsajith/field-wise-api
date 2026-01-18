package com.unnathy.fieldwise.attendance;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.attendance.AttendanceDTO;
import com.unnathy.fieldwise.attendance.Attendance;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.attendance.AttendanceRepository;
import com.unnathy.fieldwise.user.UserRepository;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AttendanceService implements BasicEntityService<AttendanceDTO, Long> {

    private final AttendanceRepository repository;
    private final ModelMapperService modelMapperService;
    private final UserRepository userRepository;

    @Override
    public AttendanceDTO post(AttendanceDTO data, String authorization, User principal) throws UnnathyError {
        Long userId = data.getUserId();
        if (userId == null && principal != null) {
            userId = principal.getId();
        }
        if (userId == null) {
            throw new UnnathyError("BAD_REQUEST", "User id is required", null);
        }

        Optional<Attendance> latestOpt = repository.findTopByUserIdOrderByIdDesc(userId);
        Attendance saved;
        if (latestOpt.isPresent() && latestOpt.get().getPunchOutTime() == null) {
            Attendance latest = latestOpt.get();
            Instant punchOutTime = Instant.now();

            latest.setPunchOutTime(punchOutTime);
            latest.setPunchOutLatitude(data.getLatitude());
            latest.setPunchOutLongitude(data.getLongitude());
            if (data.getBikePhoto() != null) {
                latest.setBikePhotoOut(data.getBikePhoto());
            }
            if (data.getBikeStartKm() != null) {
                latest.setBikeEndKm(data.getBikeStartKm());
            }
            if (data.getOtherNote() != null) {
                latest.setOtherNoteEnd(data.getOtherNote());
            }
            if (latest.getPunchInTime() != null) {
                long minutes = Duration.between(latest.getPunchInTime(), punchOutTime).toMinutes();
                if (minutes >= 0) {
                    latest.setWorkDurationMinutes((int) minutes);
                }
            }

            saved = repository.save(latest);
        } else {
            Attendance entity = new Attendance();

            entity.setBikeStartKm(data.getBikeStartKm());
            entity.setOtherNote(data.getOtherNote());
            entity.setBikePhoto(data.getBikePhoto());
            entity.setLatitude(data.getLatitude());
            entity.setLongitude(data.getLongitude());
            entity.setSelectedMode(data.getSelectedMode());

            entity.setUserId(userId);
            entity.setPunchInTime(Instant.now());
            saved = repository.save(entity);
        }
        AttendanceDTO response = modelMapperService.map(saved, AttendanceDTO.class);
        return enrichWithUser(enrichWithPunchStatus(response));
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
                .map(this::enrichWithPunchStatus)
                .map(this::enrichWithUser)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, AttendanceDTO.class))
                .map(this::enrichWithPunchStatus)
                .map(this::enrichWithUser)
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Attendance not found", null));
    }

    private AttendanceDTO enrichWithPunchStatus(AttendanceDTO dto) {
        if (dto == null) {
            return null;
        }
        dto.setPunchStatus(dto.getPunchOutTime() == null ? "Punched-In" : "Punched-Out");
        dto.setLastActionTime(dto.getPunchOutTime() == null ? dto.getPunchInTime() : dto.getPunchOutTime());
        return dto;
    }

    private AttendanceDTO enrichWithUser(AttendanceDTO dto) {
        if (dto == null || dto.getUserId() == null) {
            return dto;
        }
        userRepository.findById(dto.getUserId()).ifPresent(user -> {
            dto.setUserName(resolveUserName(user));
            dto.setProfilePhotoUrl(user.getProfilePhotoUrl());
        });
        return dto;
    }

    private String resolveUserName(User user) {
        String username = user.getUsername();
        if (username != null && !username.isBlank()) {
            return username;
        }
        String firstName = user.getFirstName() == null ? "" : user.getFirstName().trim();
        String lastName = user.getLastName() == null ? "" : user.getLastName().trim();
        String fullName = (firstName + " " + lastName).trim();
        return fullName.isEmpty() ? null : fullName;
    }
}
