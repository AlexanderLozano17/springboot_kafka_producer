package com.demo.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.core.entities.Commentary;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

}
