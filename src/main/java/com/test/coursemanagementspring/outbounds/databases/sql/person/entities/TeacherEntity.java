package com.test.coursemanagementspring.outbounds.databases.sql.person.entities;

import com.test.coursemanagementspring.core.person.entities.Person;
import com.test.coursemanagementspring.core.person.entities.Teacher;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("teacher")
public class TeacherEntity extends PersonEntity {
    public TeacherEntity() {
        super();
    }

    public TeacherEntity(int id, String name) {
       super(id, name);
    }

    @Override
    public Person toCorePerson() {
        return new Teacher(this.id, this.name);
    }
}
