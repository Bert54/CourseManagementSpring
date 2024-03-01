package com.test.coursemanagementspring.outbounds.databases.sql.membership;

import com.test.coursemanagementspring.core.services.classs.adapters.ClassDaoAdapter;
import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.transformer.MembershipTransformerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.repositories.MembershipRepository;

public class Dependencies {
    MembershipRepository membershipRepository;
    LoggerAdapter logger;
    MembershipTransformerAdapter membershipTransformer;
    ClassDaoAdapter classDao;
}
