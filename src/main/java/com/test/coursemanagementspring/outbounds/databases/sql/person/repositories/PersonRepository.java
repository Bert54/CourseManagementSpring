package com.test.coursemanagementspring.outbounds.databases.sql.person.repositories;

import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {
    @Query("SELECT person FROM PersonEntity person WHERE person.id = :id")
    PersonEntity find(int id);

    @Query("SELECT person FROM PersonEntity person WHERE person.name = :name")
    PersonEntity find(String name);
}
