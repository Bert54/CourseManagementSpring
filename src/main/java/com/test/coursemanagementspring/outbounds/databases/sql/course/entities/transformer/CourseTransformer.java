package com.test.coursemanagementspring.outbounds.databases.sql.course.entities.transformer;

import com.test.coursemanagementspring.core.services.course.entities.Course;
import com.test.coursemanagementspring.outbounds.databases.sql.course.entities.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseTransformer implements CourseTransformerAdapter {
    @Override
    public CourseEntity toCourseEntity(Course course) {
        return new CourseEntity(course.getTeacherId(), course.getClassName(), course.getTitle(), course.getContent());
    }
}
