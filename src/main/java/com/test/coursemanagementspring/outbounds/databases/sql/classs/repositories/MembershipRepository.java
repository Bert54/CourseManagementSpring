package com.test.coursemanagementspring.outbounds.databases.sql.classs.repositories;

import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.MembershipEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.MembershipEntityPK;
import org.springframework.data.repository.CrudRepository;

public interface MembershipRepository extends CrudRepository<MembershipEntity, MembershipEntityPK> {
}
