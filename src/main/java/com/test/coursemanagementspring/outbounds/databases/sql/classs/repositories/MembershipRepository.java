package com.test.coursemanagementspring.outbounds.databases.sql.classs.repositories;

import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.ClassEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.MembershipEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.MembershipEntityPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface MembershipRepository extends CrudRepository<MembershipEntity, MembershipEntityPK> {
    @NonNull
    @Override
    <S extends MembershipEntity> S save(@NonNull S membership);
}
