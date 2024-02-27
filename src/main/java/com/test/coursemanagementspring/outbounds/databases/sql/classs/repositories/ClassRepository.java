package com.test.coursemanagementspring.outbounds.databases.sql.classs.repositories;

import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.ClassEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface ClassRepository extends CrudRepository<ClassEntity, Integer> {
    @Query("SELECT class FROM ClassEntity class LEFT JOIN MembershipEntity m ON class.name = m.className WHERE class.name = :name")
    ClassEntity find(String name);

    @NonNull
    @Override
    <S extends ClassEntity> S save(@NonNull S cls);

    @Transactional
    @Modifying
    @Query("DELETE FROM ClassEntity AS class WHERE class.name = :name")
    int delete(String name);
}
