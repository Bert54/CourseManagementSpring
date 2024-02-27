package com.test.coursemanagementspring.outbounds.databases.sql.classs.entities;

import com.test.coursemanagementspring.core.services.classs.entities.Class;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.test.coursemanagementspring.core.services.classs.entities.Class.NAME_MAX_LENGTH;
import static com.test.coursemanagementspring.core.services.classs.entities.Class.NAME_MIN_LENGTH;

@Entity
@Table(name = "Class")
public class ClassEntity {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    private String name;

    @OneToMany(mappedBy = "classEntity")
    private List<MembershipEntity> memberships;

    public ClassEntity() {}

    public ClassEntity(String name) {
        this.name = name;
    }

    public ClassEntity(int id, String name) {
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

    public Class toCoreClass(boolean transformPerson) {
        Class cls = new Class(this.id, this.name);
        if (!transformPerson) {
            return cls;
        }

        if (this.memberships != null) {
            for (MembershipEntity m: this.memberships) {
                cls.addMember(m.getPersonEntity().toCorePerson(false));
            }
        }
        return cls;
    }
}
