package com.pims.server.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pims.server.model.Answer;

public interface AnswerService {

	List<Answer> findByQuestionId(UUID questionId);

		
	Optional<Answer> findById(UUID answerId);
	void delete(Answer answer);
	Answer save(Answer answer);
}
