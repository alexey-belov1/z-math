package ru.zmath.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zmath.back.repository.SubjectRepository;
import ru.zmath.back.service.dto.SubjectDTO;
import ru.zmath.back.service.mapper.SubjectMapper;

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

    @Transactional(readOnly = true)
    public List<SubjectDTO> findAll() {
        return subjectRepository.findAll().stream()
            .map(subjectMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
