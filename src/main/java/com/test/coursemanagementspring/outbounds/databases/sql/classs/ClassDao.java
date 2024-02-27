package com.test.coursemanagementspring.outbounds.databases.sql.classs;

import com.test.coursemanagementspring.core.common.errors.AlreadyExistsException;
import com.test.coursemanagementspring.core.common.errors.DeletionException;
import com.test.coursemanagementspring.core.common.errors.NotFoundException;
import com.test.coursemanagementspring.core.services.classs.adapters.ClassDaoAdapter;
import com.test.coursemanagementspring.core.services.classs.entities.Class;
import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.ClassEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.transformer.ClassTransformerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.repositories.ClassRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
public class ClassDao implements ClassDaoAdapter {
    private final ClassRepository classRepository;
    private final LoggerAdapter logger;
    private final ClassTransformerAdapter classTransformer;

    public ClassDao(ClassRepository classRepository, LoggerAdapter logger, ClassTransformerAdapter classTransformer) {
        this.classRepository = classRepository;
        this.logger = logger;
        this.classTransformer = classTransformer;
    }

    @Override
    public Class find(String name) {
        ClassEntity cls = this.classRepository.find(name);
        if (cls == null) {
            String message = String.format("Class with name '%s' was not found", name);
            this.logger.info(message);
            throw new NotFoundException(message);
        }

        return cls.toCoreClass(true);
    }

    @Override
    public Class save(Class cls) {
        ClassEntity savedClass;
        try {
            savedClass = this.classRepository.save(this.toClassEntity(cls));
        } catch (DataIntegrityViolationException e) {
            String message = String.format("Class with name '%s' already exists", cls.getName());
            this.logger.info(message);
            throw new AlreadyExistsException(message);
        }

        return savedClass.toCoreClass(true);
    }

    @Override
    public Class delete(String name) {
        Class cls = this.find(name);
        int deletedRows = this.classRepository.delete(name);
        if (deletedRows == 0) {
            String message = String.format("Could not delete class with name '%s'", cls.getName());
            this.logger.info(message);
            throw new DeletionException(message);
        }

        return cls;
    }

    public ClassEntity toClassEntity(Class cls) {
        return this.classTransformer.toClassEntity(cls);
    }
}
