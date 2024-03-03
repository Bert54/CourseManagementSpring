package com.test.coursemanagementspring.outbounds.databases.sql.course.entities;

import com.test.coursemanagementspring.core.services.course.entities.Course;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.ClassEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import static com.test.coursemanagementspring.core.services.course.entities.Course.*;

@Entity
@Table(name = "Course")
public class CourseEntity {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "student_class")
    private String className;

    @Column
    @Size(min = TITLE_MIN_LENGTH, max = TITLE_MAX_LENGTH)
    private String title;

    @Column
    @Size(min = CONTENT_MIN_LENGTH)
    private String content;

    @ManyToOne
    @JoinColumn(name="student_class", referencedColumnName = "name", insertable=false, updatable=false)
    private ClassEntity cls;

    public CourseEntity() {}

    public CourseEntity(int teacherId, String className, String title, String content) {
        this.teacherId = teacherId;
        this.className = className;
        this.title = title;
        this.content = content;
    }

    public CourseEntity(int id, int teacherId, String className, String title, String content) {
        this.id = id;
        this.teacherId = teacherId;
        this.className = className;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ClassEntity getCls() {
        return cls;
    }

    public void setCls(ClassEntity cls) {
        this.cls = cls;
    }

    public Course toCoreCourse() {
        return new Course(this.id, this.teacherId, this.className, this.title, this.content, this.cls.toCoreClass(false));
    }
}
