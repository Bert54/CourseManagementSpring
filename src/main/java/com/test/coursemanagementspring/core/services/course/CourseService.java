package com.test.coursemanagementspring.core.services.course;

import com.test.coursemanagementspring.core.services.course.adapters.CourseDaoAdapter;
import com.test.coursemanagementspring.core.services.course.adapters.CourseServiceAdapter;
import com.test.coursemanagementspring.core.services.course.entities.Course;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements CourseServiceAdapter {
    private final CourseDaoAdapter courseDao;

    public CourseService(CourseDaoAdapter courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Course addCourse(Course course) {
        return this.courseDao.save(course);
    }
}
