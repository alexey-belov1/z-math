package ru.zmath.rest.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zmath.rest.Specification.QueryService;
import ru.zmath.rest.model.*;
import ru.zmath.rest.repository.TaskRepository;
import ru.zmath.rest.service.criteria.TaskCriteria;

import javax.persistence.criteria.JoinType;

@Service
public class TaskQueryService extends QueryService<Task> {

    private final TaskRepository taskRepository;

    public TaskQueryService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public Page<Task> findByCriteria(TaskCriteria criteria, Pageable page) {
        Specification<Task> specification = createSpecification(criteria);
        return taskRepository.findAll(specification, page);
    }

    // TODO - разобраться как работать с метамоделями (в pom)
    protected Specification<Task> createSpecification(TaskCriteria criteria) {
        Specification<Task> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getSubjectId() != null) {
                specification = specification.and(buildSpecification(criteria.getSubjectId(),
                    root -> root.join(Task_.subject, JoinType.LEFT).get(Subject_.id)));
            }
            if (criteria.getStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getStatusId(),
                    root -> root.join(Task_.status, JoinType.LEFT).get(Status_.id)));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), Task_.archived));
            }
        }
        return specification;
    }
}
