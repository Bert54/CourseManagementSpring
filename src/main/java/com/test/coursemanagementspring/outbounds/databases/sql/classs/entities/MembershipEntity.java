package com.test.coursemanagementspring.outbounds.databases.sql.classs.entities;

import com.test.coursemanagementspring.core.services.classs.entities.Membership;
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

    @ManyToOne
    @JoinColumn(name="person_id", referencedColumnName = "id")
    private PersonEntity personEntity;

    @ManyToOne
    @JoinColumn(name="class_name", referencedColumnName = "name")
    private ClassEntity classEntity;

    public MembershipEntity(int personId, String className) {
        this.personId = personId;
        this.className = className;
    }

    public MembershipEntity() {}

    public ClassEntity getClassEntity() {
        return this.classEntity;
    }

    public Membership toCoreMembership() {
        return new Membership(this.personId, this.className);
    }
}
