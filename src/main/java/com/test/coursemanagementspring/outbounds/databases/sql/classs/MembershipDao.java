package com.test.coursemanagementspring.outbounds.databases.sql.classs;

import com.test.coursemanagementspring.core.common.errors.AlreadyExistsException;
import com.test.coursemanagementspring.core.common.errors.NotFoundException;
import com.test.coursemanagementspring.core.services.classs.adapters.ClassDaoAdapter;
import com.test.coursemanagementspring.core.services.classs.adapters.MembershipDaoAdapter;
import com.test.coursemanagementspring.core.services.classs.entities.Membership;
import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.MembershipEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.transformer.MembershipTransformerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.repositories.MembershipRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class MembershipDao implements MembershipDaoAdapter {
    private final LoggerAdapter logger;
    private final MembershipTransformerAdapter membershipTransformer;
    private final MembershipRepository membershipRepository;
    private final ClassDaoAdapter classDao;

    public MembershipDao(
            LoggerAdapter logger,
            MembershipTransformerAdapter membershipTransformer,
            MembershipRepository membershipRepository,
            ClassDaoAdapter classDao
    ) {
        this.logger = logger;
        this.membershipTransformer = membershipTransformer;
        this.membershipRepository = membershipRepository;
        this.classDao = classDao;
    }

    @Override
    public Membership save(Membership membership) {
        // first check for existence of class
        this.classDao.find(membership.getClassName());
        // then check for existence of membership
        try {
            this.find(membership.getPersonId(), membership.getClassName(), false);
            // did not throw, so membership already exists
            String message = String.format("Membership for person with id '%d' and class '%s' already exists", membership.getPersonId(), membership.getClassName());
            this.logger.info(message);
            throw new AlreadyExistsException(message);
        } catch (NotFoundException ignored) {
            // do nothing
        }

        this.membershipRepository.save(membership.getPersonId(), membership.getClassName());

        return membership;
    }

    @Override
    public Membership find(int personId, String className, boolean log) {
        MembershipEntity membership = this.membershipRepository.find(personId, className);
        if (membership == null) {
            String message = String.format("Membership for person with id '%d' and class '%s' was not found", personId, className);
            if (log) {
                this.logger.info(message);
            }
            throw new NotFoundException(message);
        }

        return membership.toCoreMembership();
    }
}
