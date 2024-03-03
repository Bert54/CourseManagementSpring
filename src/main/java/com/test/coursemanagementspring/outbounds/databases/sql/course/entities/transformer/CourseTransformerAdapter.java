package com.test.coursemanagementspring.outbounds.databases.sql.course.entities.transformer;

import com.test.coursemanagementspring.core.services.course.entities.Course;
import com.test.coursemanagementspring.outbounds.databases.sql.course.entities.CourseEntity;

public interface CourseTransformerAdapter {
    CourseEntity toCourseEntity(Course course);
}
