package com.test.coursemanagementspring.outbounds.databases.sql.person.entities;

import com.test.coursemanagementspring.core.services.person.entities.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import static com.test.coursemanagementspring.core.services.person.entities.Person.*;

@Entity
@Table(name = "Person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class PersonEntity {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    @Column(unique = true)
    protected String name;

    public PersonEntity() {

    }

    protected PersonEntity(String name) {
        this.name = name;
    }

    protected PersonEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract public Person toCorePerson();
}
