package com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.transformer;

import com.test.coursemanagementspring.core.services.classs.entities.Class;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.ClassEntity;

public interface ClassTransformerAdapter {
    ClassEntity toClassEntity(Class cls);
}
