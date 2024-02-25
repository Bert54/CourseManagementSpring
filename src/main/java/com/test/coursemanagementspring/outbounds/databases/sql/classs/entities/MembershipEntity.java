package com.test.coursemanagementspring.outbounds.databases.sql.classs.entities;

import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.PersonEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "ClassMembership")
@IdClass(MembershipEntityPK.class)
public class MembershipEntity {
    @Column(name="person_id")
    @Id
    private int personId;

    @Column(name="class_name")
    @Id
    private String className;

    @OneToOne
    @JoinColumn(name="person_id", referencedColumnName = "id")
    private PersonEntity personEntity;

    @OneToOne
    @JoinColumn(name="class_name", referencedColumnName = "name")
    private ClassEntity classEntity;

    public ClassEntity getClassEntity() {
        return this.classEntity;
    }
}
