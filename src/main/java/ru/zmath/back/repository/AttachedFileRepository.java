package ru.zmath.back.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.back.model.AttachedFile;

public interface AttachedFileRepository extends CrudRepository<AttachedFile, Integer> {
}
