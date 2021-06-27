package ru.zmath.rest.service;

import org.springframework.stereotype.Service;
import ru.zmath.rest.repository.SubjectRepository;
import ru.zmath.rest.service.dto.SubjectDTO;
import ru.zmath.rest.service.mapper.SubjectMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final SubjectMapper subjectMapper;

    public SubjectService(final SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    public List<SubjectDTO> findAll() {
        return subjectRepository.findAll().stream()
            .map(subjectMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
