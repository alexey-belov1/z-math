package ru.zmath.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zmath.back.repository.StatusRepository;
import ru.zmath.back.service.dto.StatusDTO;
import ru.zmath.back.service.mapper.StatusMapper;

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

    @Transactional(readOnly = true)
    public List<StatusDTO> findAll() {
        return statusRepository.findAll().stream()
            .map(statusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
