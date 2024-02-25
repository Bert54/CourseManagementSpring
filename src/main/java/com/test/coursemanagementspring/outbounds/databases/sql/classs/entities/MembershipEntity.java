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

    public MembershipEntity(int personId, String className) {
        this.personId = personId;
        this.className = className;
    }

    @OneToOne
    @Transient
    @JoinColumn(referencedColumnName = "id")
    private PersonEntity personEntity;

    @OneToOne
    @Transient
    @JoinColumn(referencedColumnName = "name")
    private ClassEntity classEntity;

    public MembershipEntity() {}

    public ClassEntity getClassEntity() {
        return this.classEntity;
    }
}
