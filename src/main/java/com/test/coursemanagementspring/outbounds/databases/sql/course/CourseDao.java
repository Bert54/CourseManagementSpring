package com.test.coursemanagementspring.outbounds.databases.sql.course;

import com.test.coursemanagementspring.core.services.course.adapters.CourseDaoAdapter;
import com.test.coursemanagementspring.core.services.course.entities.Course;
import com.test.coursemanagementspring.outbounds.databases.sql.course.entities.CourseEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.course.entities.transformer.CourseTransformerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.course.repositories.CourseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDao implements CourseDaoAdapter {
    private final CourseRepository courseRepository;
    private final CourseTransformerAdapter courseTransformer;

    public CourseDao(CourseRepository courseRepository, CourseTransformerAdapter courseTransformer) {
        this.courseRepository = courseRepository;
        this.courseTransformer = courseTransformer;
    }

    @Override
    public Course save(Course course) {
        CourseEntity courseEntity = this.toCourseEntity(course);
        return this.courseRepository.save(courseEntity).toCoreCourse();
    }

    public CourseEntity toCourseEntity(Course course) {
        return this.courseTransformer.toCourseEntity(course);
    }
}
