package com.test.coursemanagementspring.outbounds.databases.sql.membership;

import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.ClassDao;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.MembershipDao;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.transformer.MembershipTransformerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.repositories.MembershipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class MembershipDaoTest {
    private Dependencies dependencies;
    private MembershipDao membershipDao;

    @BeforeEach
    public void setupDependencies() {
        this.dependencies = new Dependencies();
        this.dependencies.logger = Mockito.mock(LoggerAdapter.class);
        this.dependencies.membershipRepository = Mockito.mock(MembershipRepository.class);
        this.dependencies.membershipTransformer = Mockito.mock(MembershipTransformerAdapter.class);
        this.dependencies.classDao = Mockito.mock(ClassDao.class);

        this.membershipDao = new MembershipDao(
                dependencies.logger,
                dependencies.membershipTransformer,
                dependencies.membershipRepository,
                dependencies.classDao);
    }
}
