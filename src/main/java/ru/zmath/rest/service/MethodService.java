package ru.zmath.rest.service;

import org.springframework.stereotype.Service;
import ru.zmath.rest.model.Method;
import ru.zmath.rest.repository.MethodRepository;

import java.util.List;

@Service
public class MethodService {

    private final MethodRepository methodRepository;

    public MethodService(final MethodRepository methodRepository) {
        this.methodRepository = methodRepository;
    }

    public List<Method> findAll() {
        return this.methodRepository.findAll();
    }
}
