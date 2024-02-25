package com.test.coursemanagementspring.core.services.classs.adapters;

import com.test.coursemanagementspring.core.services.classs.entities.Membership;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.MembershipEntity;

public interface MembershipDaoAdapter {
    Membership save(Membership membership);
    Membership find(int personId, String className, boolean log);
}
