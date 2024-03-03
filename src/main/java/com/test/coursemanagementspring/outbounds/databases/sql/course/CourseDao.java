package com.test.coursemanagementspring.outbounds.databases.sql.course;

import com.test.coursemanagementspring.core.services.classs.adapters.ClassDaoAdapter;
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
    private final ClassDaoAdapter classDao;

    public CourseDao(CourseRepository courseRepository, CourseTransformerAdapter courseTransformer, ClassDaoAdapter classDao) {
        this.courseRepository = courseRepository;
        this.courseTransformer = courseTransformer;
        this.classDao = classDao;
    }

    @Override
    public Course save(Course course) {
        // check for existence of class first
        this.classDao.find(course.getClassName());

        CourseEntity courseEntity = this.toCourseEntity(course);
        return this.courseRepository.save(courseEntity).toCoreCourse();
    }

    public CourseEntity toCourseEntity(Course course) {
        return this.courseTransformer.toCourseEntity(course);
    }
}
