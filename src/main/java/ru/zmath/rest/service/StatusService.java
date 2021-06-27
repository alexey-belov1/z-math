package ru.zmath.rest.service;

import org.springframework.stereotype.Service;
import ru.zmath.rest.repository.StatusRepository;
import ru.zmath.rest.service.dto.StatusDTO;
import ru.zmath.rest.service.mapper.StatusMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    private final StatusMapper statusMapper;

    public StatusService(StatusRepository statusRepository, StatusMapper statusMapper) {
        this.statusRepository = statusRepository;
        this.statusMapper = statusMapper;
    }

    public List<StatusDTO> findAll() {
        return statusRepository.findAll().stream()
            .map(statusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
