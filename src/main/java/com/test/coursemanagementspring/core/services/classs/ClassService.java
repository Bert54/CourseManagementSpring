package com.test.coursemanagementspring.core.services.classs;

import com.test.coursemanagementspring.core.services.classs.adapters.ClassDaoAdapter;
import com.test.coursemanagementspring.core.services.classs.adapters.ClassServiceAdapter;
import com.test.coursemanagementspring.core.services.classs.entities.Class;
import org.springframework.stereotype.Component;

@Component
public class ClassService implements ClassServiceAdapter {
    private final ClassDaoAdapter classDao;

    public ClassService(ClassDaoAdapter classDao) {
        this.classDao = classDao;
    }

    @Override
    public Class addClass(Class cls) {
        return this.classDao.save(cls);
    }

    @Override
    public Class getClass(String name) {
        return this.classDao.find(name);
    }

    @Override
    public Class deleteClass(String name) {
        return this.classDao.delete(name);
    }
}
