package com.test.coursemanagementspring.outbounds.databases.sql.person.entities.transformer;

import com.test.coursemanagementspring.core.services.person.entities.Person;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.PersonEntity;

public interface PersonTransformerAdapter {
    PersonEntity toPersonEntity(Person person);
}
