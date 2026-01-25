package com.unnathy.fieldwise.route;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.route.RouteDTO;
import com.unnathy.fieldwise.route.Route;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.route.RouteRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class RouteService implements BasicEntityService<RouteDTO, RouteDTO, Long> {

    private final RouteRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public RouteDTO post(RouteDTO data, String authorization, User principal) throws UnnathyError {
        Route entity = modelMapperService.map(data, Route.class);
        Route saved = repository.save(entity);
        return modelMapperService.map(saved, RouteDTO.class);
    }

    @Override
    public RouteDTO patch(RouteDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public RouteDTO put(RouteDTO data, String authorization, User principal) throws UnnathyError {
        Route entity = modelMapperService.map(data, Route.class);
        Route saved = repository.save(entity);
        return modelMapperService.map(saved, RouteDTO.class);
    }

    @Override
    public List<RouteDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, RouteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RouteDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, RouteDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Route not found", null));
    }
}


