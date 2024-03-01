package com.test.coursemanagementspring.core.services.classs;

import com.test.coursemanagementspring.core.services.classs.adapters.MembershipDaoAdapter;
import com.test.coursemanagementspring.core.services.classs.adapters.MembershipServiceAdapter;
import com.test.coursemanagementspring.core.services.classs.entities.Membership;
import org.springframework.stereotype.Service;

@Service
public class MembershipService implements MembershipServiceAdapter {
    private final MembershipDaoAdapter membershipDao;

    public MembershipService(MembershipDaoAdapter membershipDao) {
        this.membershipDao = membershipDao;
    }

    @Override
    public Membership addMembership(Membership membership) {
        return this.membershipDao.save(membership);
    }

    @Override
    public Membership removeMembership(int personId, String className) {
        return this.membershipDao.delete(personId, className);
    }
}
