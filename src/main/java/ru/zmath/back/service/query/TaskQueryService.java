package ru.zmath.back.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zmath.back.Specification.QueryService;
import ru.zmath.back.model.Status_;
import ru.zmath.back.model.Subject_;
import ru.zmath.back.model.Task;
import ru.zmath.back.model.Task_;
import ru.zmath.back.repository.TaskRepository;
import ru.zmath.back.service.criteria.TaskCriteria;
import ru.zmath.back.service.dto.TaskDTO;
import ru.zmath.back.service.mapper.TaskMapper;

import javax.persistence.criteria.JoinType;

@Service
public class TaskQueryService extends QueryService<Task> {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public TaskQueryService(final TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Transactional(readOnly = true)
    public Page<TaskDTO> findByCriteria(TaskCriteria criteria, Pageable page) {
        Specification<Task> specification = createSpecification(criteria);
        return taskRepository.findAll(specification, page)
            .map(taskMapper::toDto);
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
