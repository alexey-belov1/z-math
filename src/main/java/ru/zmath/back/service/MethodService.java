package ru.zmath.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zmath.back.repository.MethodRepository;
import ru.zmath.back.service.dto.MethodDTO;
import ru.zmath.back.service.mapper.MethodMapper;

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
