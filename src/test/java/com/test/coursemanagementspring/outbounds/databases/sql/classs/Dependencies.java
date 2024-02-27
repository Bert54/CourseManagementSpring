package com.test.coursemanagementspring.outbounds.databases.sql.classs;

import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.transformer.ClassTransformerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.repositories.ClassRepository;

public class Dependencies {
    ClassRepository classRepository;
    LoggerAdapter logger;
    ClassTransformerAdapter classTransformer;
}
