package com.test.coursemanagementspring.outbounds.databases.sql.classs.repositories;

import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.ClassEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.MembershipEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.MembershipEntityPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface MembershipRepository extends CrudRepository<MembershipEntity, MembershipEntityPK> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ClassMembership(person_id, class_name) VALUES (:personId, :className)", nativeQuery = true)
    void save(int personId, String className);

    @NonNull
    @Override
    <S extends MembershipEntity> S save(@NonNull S membership);

    @Query("SELECT membership FROM MembershipEntity membership WHERE membership.className = :className AND membership.personId = :personId")
    MembershipEntity find(int personId, String className);

    @Transactional
    @Modifying
    @Query("DELETE FROM MembershipEntity membership WHERE membership.className = :name AND membership.personId = :personId")
    int delete(int personId, String name);
}
