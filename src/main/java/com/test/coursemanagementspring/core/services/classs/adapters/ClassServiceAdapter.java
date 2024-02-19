package com.test.coursemanagementspring.core.services.classs.adapters;

import com.test.coursemanagementspring.core.services.classs.entities.Class;

public interface ClassServiceAdapter {
    Class addClass(Class cls);

    Class getClass(String name);

    Class deleteClass(String name);
}
