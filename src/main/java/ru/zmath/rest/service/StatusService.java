package ru.zmath.rest.service;

import org.springframework.stereotype.Service;
import ru.zmath.rest.model.Status;
import ru.zmath.rest.model.Subject;
import ru.zmath.rest.repository.StatusRepository;

import java.util.List;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }


    public List<Status> findAll() {
        return this.statusRepository.findAll();
    }
}
