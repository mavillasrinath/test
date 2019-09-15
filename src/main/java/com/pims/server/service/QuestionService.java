package com.pims.server.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pims.server.model.Question;

public interface QuestionService {
	
	Optional<Question> findById(UUID questionId);

	boolean existsById(UUID questionId);

	List<Question> findAll();

	Question save(@Valid Question question);

	void delete(Question question);

}
