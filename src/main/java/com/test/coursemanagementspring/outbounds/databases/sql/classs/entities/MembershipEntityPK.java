package com.test.coursemanagementspring.outbounds.databases.sql.classs.entities;

import java.io.Serializable;

public class MembershipEntityPK implements Serializable {
    public int personId;
    public String className;

    @Override
    public int hashCode() {
        return this.personId + this.className.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof MembershipEntityPK other)) {
            return false;
        }

        return this.className.equals(other.className) && this.personId == other.personId;
    }
}
