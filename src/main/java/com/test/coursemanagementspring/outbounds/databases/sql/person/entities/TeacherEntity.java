package com.test.coursemanagementspring.outbounds.databases.sql.person.entities;

import com.test.coursemanagementspring.core.services.person.entities.Person;
import com.test.coursemanagementspring.core.services.person.entities.Teacher;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("teacher")
public class TeacherEntity extends PersonEntity {
    public TeacherEntity() {
        super();
    }

    public TeacherEntity(String name) {
        super(name);
    }

    public TeacherEntity(int id, String name) {
       super(id, name);
    }

    @Override
    public Person toCorePerson(boolean transformClass) {
        Person p = new Teacher(this.id, this.name);
        if (transformClass) {
            this.toCorePersonSetClasses(p);
        }
        return p;
    }
}
