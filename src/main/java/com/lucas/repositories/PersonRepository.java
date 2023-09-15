package com.lucas.repositories;

import org.springframework.stereotype.Repository;

import com.lucas.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{}
