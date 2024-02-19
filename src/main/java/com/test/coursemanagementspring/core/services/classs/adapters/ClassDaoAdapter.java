package com.test.coursemanagementspring.core.services.classs.adapters;

import com.test.coursemanagementspring.core.services.classs.entities.Class;

public interface ClassDaoAdapter {
    Class find(String name);

    Class save(Class cls);

    Class delete(String name);
}
