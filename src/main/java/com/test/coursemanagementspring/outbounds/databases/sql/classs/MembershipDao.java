package com.test.coursemanagementspring.outbounds.databases.sql.classs;

import com.test.coursemanagementspring.core.common.errors.AlreadyExistsException;
import com.test.coursemanagementspring.core.services.classs.adapters.ClassDaoAdapter;
import com.test.coursemanagementspring.core.services.classs.adapters.MembershipDaoAdapter;
import com.test.coursemanagementspring.core.services.classs.entities.Membership;
import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
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
        /// first check for existence of class
        this.classDao.find(membership.getClassName());
        try {
            this.membershipRepository.save(this.membershipTransformer.toMembershipEntity(membership));
        } catch (DataIntegrityViolationException e) {
            String message = String.format("Membership for person with id '%d' and class '%s' already exists", membership.getPersonId(), membership.getClassName());
            this.logger.info(message);
            throw new AlreadyExistsException(message);
        }
        return membership;
    }
}
