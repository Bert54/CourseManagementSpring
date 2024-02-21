package com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.transformer;

import com.test.coursemanagementspring.core.services.classs.entities.Class;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.ClassEntity;
import org.springframework.stereotype.Component;

@Component
public class ClassTransformer implements ClassTransformerAdapter {
    @Override
    public ClassEntity toClassEntity(Class cls) {
        return new ClassEntity(cls.getId(), cls.getName());
    }
}
