package ru.zmath.rest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zmath.rest.repository.MethodRepository;
import ru.zmath.rest.service.dto.MethodDTO;
import ru.zmath.rest.service.mapper.MethodMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MethodService {

    private final MethodRepository methodRepository;

    private final MethodMapper methodMapper;

    public MethodService(final MethodRepository methodRepository, MethodMapper methodMapper) {
        this.methodRepository = methodRepository;
        this.methodMapper = methodMapper;
    }

    @Transactional(readOnly = true)
    public List<MethodDTO> findAll() {
        return methodRepository.findAll().stream()
            .map(methodMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
