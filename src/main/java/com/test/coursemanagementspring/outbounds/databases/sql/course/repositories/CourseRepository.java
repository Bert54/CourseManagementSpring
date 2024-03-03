package com.test.coursemanagementspring.outbounds.databases.sql.course.repositories;

import com.test.coursemanagementspring.outbounds.databases.sql.course.entities.CourseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {
    @Query("SELECT course FROM CourseEntity course " +
            "JOIN ClassEntity c ON course.className = c.name " +
            "WHERE course.id = :id AND course.teacherId = :teacherId")
    CourseEntity find(int id, int teacherId);

    @NonNull
    @Override
    <S extends CourseEntity> S save(@NonNull S cls);
}
