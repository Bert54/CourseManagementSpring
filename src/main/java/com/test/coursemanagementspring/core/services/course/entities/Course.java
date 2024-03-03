package com.test.coursemanagementspring.core.services.course.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.coursemanagementspring.core.services.classs.entities.Class;

public class Course {
    public static final int TITLE_MIN_LENGTH = 3;
    public static final int TITLE_MAX_LENGTH = 200;
    public static final int CONTENT_MIN_LENGTH = 1;

    int id;
    int teacherId;
    String className;
    String title;
    String content;
    @JsonProperty("class")
    Class cls;

    public Course(int teacherId, String className, String title, String content) {
        this.teacherId = teacherId;
        this.className = className;
        this.title = title;
        this.content = content;
    }

    public Course(int id, int teacherId, String className, String title, String content, Class cls) {
        this.id = id;
        this.teacherId = teacherId;
        this.className = className;
        this.title = title;
        this.content = content;
        this.cls = cls;
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

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }
}
