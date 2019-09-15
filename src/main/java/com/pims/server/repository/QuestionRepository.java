package com.pims.server.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pims.server.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
}
