package com.test.coursemanagementspring.outbounds.databases.sql.person;

import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.transformer.PersonTransformerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.person.repositories.PersonRepository;

public class Dependencies {
    LoggerAdapter logger;
    PersonRepository personRepository;
    PersonTransformerAdapter personTransformer;
}
