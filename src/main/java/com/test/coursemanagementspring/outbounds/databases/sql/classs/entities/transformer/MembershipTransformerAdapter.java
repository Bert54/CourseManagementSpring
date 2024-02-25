package com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.transformer;

import com.test.coursemanagementspring.core.services.classs.entities.Membership;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.MembershipEntity;

public interface MembershipTransformerAdapter {
    MembershipEntity toMembershipEntity(Membership membership);
}
