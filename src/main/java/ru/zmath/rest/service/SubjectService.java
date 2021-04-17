package ru.zmath.rest.service;

import org.springframework.stereotype.Service;
import ru.zmath.rest.model.Subject;
import ru.zmath.rest.repository.SubjectRepository;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(final SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> findAll() {
        return this.subjectRepository.findAll();
    }
}
