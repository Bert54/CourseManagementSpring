package com.test.coursemanagementspring.core.services.classs.adapters;

import com.test.coursemanagementspring.core.services.classs.adapters.options.MembershipSingleOption;
import com.test.coursemanagementspring.core.services.classs.entities.Membership;

public interface MembershipDaoAdapter {
    Membership save(Membership membership);
    Membership find(int personId, String className, MembershipSingleOption... options);
    Membership delete(int personId, String className);
}
