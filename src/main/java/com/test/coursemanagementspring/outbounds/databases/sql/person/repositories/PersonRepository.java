package com.test.coursemanagementspring.outbounds.databases.sql.person.repositories;

import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {
    @Query("SELECT person FROM PersonEntity person LEFT JOIN MembershipEntity m ON person.id = m.personId WHERE person.id = :id")
    PersonEntity find(int id);

    @Query("SELECT person FROM PersonEntity person LEFT JOIN MembershipEntity m ON person.id = m.personId WHERE person.name = :name")
    PersonEntity find(String name);

    @NonNull
    @Override
    <S extends PersonEntity> S save(@NonNull S person);
}
