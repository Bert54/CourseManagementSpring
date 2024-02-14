package com.test.coursemanagementspring.outbounds.databases.sql.person.entities;

import com.test.coursemanagementspring.core.person.entities.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class PersonEntity {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Size(min = 1, max = 100)
    @Column(unique = true)
    protected String name;


    public PersonEntity() {

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
