package com.test.coursemanagementspring.outbounds.databases.sql.person.entities.transformer;

import com.test.coursemanagementspring.core.services.person.entities.Person;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.AdministratorEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.PersonEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.StudentEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.TeacherEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonTransformer implements PersonTransformerAdaper {
    @Override
    public PersonEntity toPersonEntity(Person person) {
        String name = person.getName();

        return switch (person.getType()) {
            case Student -> new StudentEntity(name);
            case Administrator -> new AdministratorEntity(name);
            case Teacher -> new TeacherEntity(name);
        };
    }
}
