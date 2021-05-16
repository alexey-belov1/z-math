package ru.zmath.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.rest.model.AttachedFile;

public interface AttachedFileRepository extends CrudRepository<AttachedFile, Integer> {
}
