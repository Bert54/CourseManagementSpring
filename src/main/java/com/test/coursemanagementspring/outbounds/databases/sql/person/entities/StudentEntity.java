package com.test.coursemanagementspring.outbounds.databases.sql.person.entities;

import com.test.coursemanagementspring.core.services.person.entities.Person;
import com.test.coursemanagementspring.core.services.person.entities.Student;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("student")
public class StudentEntity extends PersonEntity {
    public StudentEntity() {
        super();
    }

    public StudentEntity(String name) {
        super(name);
    }

    public StudentEntity(int id, String name) {
        super(id, name);
    }

    @Override
    public Person toCorePerson() {
        return new Student(this.id, this.name);
    }
}
