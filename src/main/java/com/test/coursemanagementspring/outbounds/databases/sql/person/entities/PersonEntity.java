package com.test.coursemanagementspring.outbounds.databases.sql.person.entities;

import com.test.coursemanagementspring.core.services.person.entities.Person;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.MembershipEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @OneToMany
    @JoinColumn(referencedColumnName = "id")
    protected List<MembershipEntity> memberships;

    public PersonEntity() {}

    protected PersonEntity(String name) {
        this.name = name;
    }

    protected PersonEntity(int id, String name) {
        this.id = id;
        this.name = name;
        this.memberships = new ArrayList<>();
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

    public List<MembershipEntity> getMemberships() {
        return Collections.unmodifiableList(this.memberships);
    }

    public void setMemberships(List<MembershipEntity> list) {
        this.memberships = list;
    }

    protected void toCorePersonSetClasses(Person person) {
        if (person == null) {
            return;
        }

        if (this.memberships == null) {
            return;
        }

        for (MembershipEntity membership: this.memberships) {
            person.addClass(membership.getClassEntity().toCoreClass());
        }
    }

    abstract public Person toCorePerson();
}
