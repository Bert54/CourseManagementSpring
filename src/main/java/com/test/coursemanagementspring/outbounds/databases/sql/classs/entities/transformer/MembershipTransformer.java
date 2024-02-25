package com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.transformer;

import com.test.coursemanagementspring.core.services.classs.entities.Membership;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.MembershipEntity;
import org.springframework.stereotype.Component;

@Component
public class MembershipTransformer implements MembershipTransformerAdapter{
    @Override
    public MembershipEntity toMembershipEntity(Membership membership) {
        return new MembershipEntity(membership.getPersonId(), membership.getClassName());
    }
}
