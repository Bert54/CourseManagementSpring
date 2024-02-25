package com.test.coursemanagementspring.outbounds.databases.sql.person.entities;

import com.test.coursemanagementspring.core.services.person.entities.Administrator;
import com.test.coursemanagementspring.core.services.person.entities.Person;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("administrator")
public class AdministratorEntity extends PersonEntity {
    public AdministratorEntity() {
        super();
    }

    public AdministratorEntity(String name) {
        super(name);
    }

    public AdministratorEntity(int id, String name) {
        super(id, name);
    }

    @Override
    public Person toCorePerson() {
        Person p = new Administrator(this.id, this.name);
        this.toCorePersonSetClasses(p);
        return p;
    }
}
