package com.demo.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.core.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
