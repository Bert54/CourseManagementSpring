package com.test.coursemanagementspring.outbounds.databases.sql.person.entities;

import com.test.coursemanagementspring.core.person.entities.Administrator;
import com.test.coursemanagementspring.core.person.entities.Person;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("administrator")
public class AdministratorEntity extends PersonEntity {
    public AdministratorEntity() {
        super();
    }

    public AdministratorEntity(int id, String name) {
        super(id, name);
    }

    @Override
    public Person toCorePerson() {
        return new Administrator(this.id, this.name);
    }
}
